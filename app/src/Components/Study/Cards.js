import {
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  makeStyles,
  List,
  ListItemText,
  Tooltip,
  IconButton,
  Typography
  
} from '@material-ui/core'
import DeleteIcon from '@material-ui/icons/Delete'
import React, {useEffect, useState }from 'react'
import handleCatchError from '../_helpers/handleCatchServicesError'
import isAxiosError from '../_helpers/isAxiosError'
import CardService from '../../services/cards'
import AddCard from './AddCard'
import Session from './Session'

const useStyle = makeStyles(() => ({
  button: {
    background: '#00b5c2',
    marginTop: '5px',
    marginLeft: '5px',
    color: 'black',
    fontFamily: 'Roboto',
  },
  button2: {
    background: '#00b5c2',
    marginTop: '15px',
    marginBottom: '15px',
    color: 'black',
    fontFamily: 'Roboto',
  },
  textTitle: {
    color: 'black',
    marginLeft: '50px',
    fontFamily: 'Roboto',
    fontSize: 30,
    flexGrow: 1
  },
  msg: {
    color: 'black',
    marginLeft: '50px',
    fontFamily: 'Roboto',
    fontSize: 20,
    flexGrow: 1
  },
  group: {
    background: '#5be8f5 ', 
  }
  
}))
const Cards = (props) => {
  const { setOpenCards, openCards, group } = props
  console.log({group})
  const classes = useStyle()
  const [updateList, setUpdateList] = useState(false)
  const {setAlert} = props
  console.log('mysetalert',setAlert)
  const [card, setCard] = useState({ frontText: '', backText:'', isSolved:false, group: {id: group.id}, rightAnswer: false })
  const [cardList, setCardList] = useState({ cardList: null })
  const [open, setOpen] = useState(false)
  const [openSession, setOpenSession] =  useState(false)

  let sortedCards
  
  useEffect(() => {

    CardService.getCardsFromGroup(group.id)
      .then(
        (cardList) => {
          const isError =  isAxiosError(cardList,setAlert)
          if (isError) return
          sortedCards = cardList.data.sort((a, b) => a.id - b.id)
          setCardList({ cardList: sortedCards })
        }
      ).catch(error => {
        handleCatchError(error, setAlert)
      })

  }, [updateList])
      
  /**
   * Handle back to group
   */
  const handleBackToGroup = () => {
    setOpenCards(false)
  }
  /**
   * Handle open add card dialog 
   */
  const handleClickOpen = () => {
    setOpen(true)
  }
  /**
   * Handle open Session
   */
  const handleOpenSession = () => {
    setOpenSession(true)
  }
  /**
 * Handle delete card
 * @param {*} id 
 */
  const handleDelete = (id) => {
    
    CardService.deleteCard(id)
      .then(result => console.log(result))
      .catch(error => alert('etwas ist schiefgelaufen DELETE'))
    setUpdateList(!updateList)
  }
  if (openSession)
    return (
      <div>
        <Session
          openSession={openSession}
          cardList={cardList}
          group={group}
        />
      </div>
    )

  if (!cardList.cardList || cardList.cardList.length === 0)
    return (
      <div>
        <Button onClick={() => handleClickOpen()} className={classes.button}>Add Cards</Button>
        <Button onClick={() => handleBackToGroup()} className={classes.button}>Back to Groups</Button>
        <AddCard
          setAlert={setAlert}
          open={open}
          setOpen={setOpen}
          card={card}
          setCard={setCard}
          updateList={updateList}
          setUpdateList={setUpdateList}
          group={group}
        />
        <Typography>You dont have any card yet!</Typography>
      </div>)
  return (
    <div>
    
      <Button onClick={() => handleClickOpen()} className={classes.button}>Add Cards</Button>
      <Button onClick={() => handleOpenSession()} className={classes.button}>Start Session</Button>
      <Button onClick={() => handleBackToGroup()} className={classes.button}>Back to Groups</Button>
      <AddCard
        setAlert={setAlert}
        open={open}
        setOpen={setOpen}
        card={card}
        setCard={setCard}
        updateList={updateList}
        setUpdateList={setUpdateList}
        group={group}
      />
      <List>
        { cardList.cardList.map((card, index) => {
          return (
            <div key={card.id}  >
             
              <ListItemText className={classes.msg}>
                      Question:   &ldquo;{card.frontText}&ldquo;
                      Answer: &ldquo;{card.backText}&ldquo;
                    
                <Tooltip title="Delete">
                  <IconButton color="primary" onClick={() => handleDelete(card.id)}>
                    <DeleteIcon
                      fontSize="small"
                    />
                  </IconButton>
                
                </Tooltip>
                
              </ListItemText>
                 
              <div className={classes.dividerSmall} />
            </div>
          )
        })}
      </List>

    </div>
  )
}
    
export default Cards