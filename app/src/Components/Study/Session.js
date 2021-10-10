/**
 * Session View
 * @author Pamela Filipinski
 */
import {
  Button,
  makeStyles,
  Typography
    
} from '@material-ui/core'
import Box from '@mui/material/Box'
import MobileStepper from '@mui/material/MobileStepper'
import Paper from '@mui/material/Paper'
import React, {useState}from 'react'
import handleCatchError from '../_helpers/handleCatchServicesError'
import CardService from '../../services/cards'
import GroupService from '../../services/group'

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
  },
  session: {
    marginLeft: '600px ',
    marginTop: '50px'
  }
    
}))

const Session = (props) => {
  const { cardList, group, setOpenSession, setCardList, updateList, setAlert, setUpdateList } = props
  const sessionLabel = `Level ${group.level}`
  const [activeStep, setActiveStep] = useState(0)
  const [toggleAnswer, setToggleAnswer] = useState(true)
  const [nextSession, setNextSession] = useState(false) //for next level
  const [emptyList, setEmptyList] = useState(false) //for all answer right

  const classes = useStyle()
 
  var maxSteps = cardList.cardList.length
  let sortedCards
 
  /**
   * Handle right answer button.
   * Update card with rightAnswer = true
   * Set right view for next level if all cards are answered.
   * Set right view if all cards have correct anwer
   * @author Pamela Filipinski
   */
  const handleRight = () => {
    
    updateRight(cardList.cardList[activeStep].id)
    CardService.updateCardAnswer({ rightAnswer: true, isSolved: true }, cardList.cardList[activeStep].id).then(
      () => {
        if (activeStep === maxSteps - 1) {
     
          if (cardList.cardList.filter((card) => card['rightAnswer'] == false).length <= 1) {
            setEmptyList(true)
          } else {
            handleAnswer()//always show Question first
            setNextSession(true)//When all questions answered move to next level
          }
        } else {
          handleAnswer()//always show Question first
          setActiveStep((prevActiveStep) => prevActiveStep + 1)
        }
      }
    )
  }

  /**
   * Set state for card from cardList
   * Set state rightAnswer = true
   * @param {*} id 
   * @author Pamela Filipinski
   */
  const updateRight = (id)=>{
    const newList = cardList.cardList.map((item) => {
      if (item.id == id) {
        const updatedItem = {
          ...item, rightAnswer: true,
        }
        return updatedItem
      }
      return item  
    })
    const newObject = { cardList: newList }
    setCardList((prev) => (newObject))
  }

  /**
   * Set state for card from cardList
   * Set state rightAnswer = false
   * @author Pamela Filipinski
   */
  const updateWrong = (id)=>{
    const newList = cardList.cardList.map((item) => {
      if (item.id == id) {
        const updatedItem = {
          ...item, rightAnswer: false,
        }
        return updatedItem
      }
      return item     
    })
    const newObject = { cardList: newList }
    setCardList((prev) => (  newObject ))
  }
  
  /**
   * Handle wrong answer button.
   * Update card with rightAnswer = false
   * Set right view for next level if all cards are answered.
   * @author Pamela Filipinski
   */
  const handleWrong = () => {
    updateWrong(cardList.cardList[activeStep].id)
    CardService.updateCardAnswer({ rightAnswer: false, isSolved: true }, cardList.cardList[activeStep].id)
   
    if (activeStep === maxSteps - 1) {
      handleAnswer()//always show Question first
      setNextSession(true) //When all questions Answered move to next level
      
    } else {
      handleAnswer() //always show Question first
      setActiveStep((prevActiveStep) => prevActiveStep + 1)
      
    }
  }

  /**
   * Handle toggleAnswer state. 
   * Toggle between question and answer
   * @author Pamela Filipinski
   */
  const handleAnswer = () => {
    setToggleAnswer(prevToggle => !prevToggle)
  }
    
  /**
   * Handle back to cards view. 
   * All cards will be reset (rightAnswer set to false)
   * Group level wil be set to 0
   * @author Pamela Filipinski
   */
  const handleBackToCards = () => {
   
    CardService.resetCards(group.id)
    setUpdateList(!updateList)
    group.level = 0
    GroupService.updateLevel({ group }, group.id)
    CardService.getCardsFromGroup(group.id)
    setOpenSession(false)
   
  }

  /**
   * Handle next level
   * Set level to +1
   * Get only wrong answered cards
   * Start next level
   * @author Pamela Filipinski
   */
  const handleNextLevel = () => {
  
    let nextLevel = group.level + 1
    group.level = nextLevel
    GroupService.updateLevel({ group }, group.id)
    CardService.getCardsWrong(group.id).then(
      (cardList) => {
        sortedCards = cardList.data.sort((a, b) => a.id - b.id)
        setCardList({ cardList: sortedCards })
      }
     
    ).catch(error => {
      handleCatchError(error, setAlert)
    })

    setNextSession(false)
    setActiveStep(0)
  
  }

  if (emptyList)
    return (<div>
   You finished session! Congratualtions!
      <Button onClick={() => handleBackToCards()} className={classes.button}>Finish Session</Button>
   
    </div>)
  if (nextSession)
    return (<div>
      You finished level: {group.level}
      <Button onClick={() => handleNextLevel()} className={classes.button}>Next Level</Button>
      <Button onClick={() => handleBackToCards()} className={classes.button}>Finish Session</Button>
      
    </div>)
  
  return (
    <div className={classes.session}>
      <Box sx={{ maxWidth: 400, flexGrow: 1 }}>
        <Paper
          square
          elevation={0}
          sx={{
            display: 'flex',
            alignItems: 'center',
            height: 50,
            pl: 2,
            bgcolor: 'background.default',
          }}
        >
          <Typography>{sessionLabel}</Typography>
        </Paper>
        <Box sx={{ height: 255, maxWidth: 400, width: '100%', p: 2 }}>
         
          {toggleAnswer ? cardList.cardList[activeStep]?.frontText : cardList.cardList[activeStep]?.backText } 
          <Button onClick={() => handleAnswer()} className={classes.button}>{toggleAnswer?'Show Answer':'Show Question'}</Button>
         
        </Box>
        <MobileStepper
          variant="text"
          steps={maxSteps}
          position="static"
          activeStep={activeStep}
          nextButton={
            <Button
              size="small"
              onClick={() => handleRight()}
              disabled={false}
            >
            Right
            </Button>
          }
          backButton={
            <Button size="small" onClick={() => handleWrong()} disabled={false}>
                  Wrong
                  
            </Button>
          }
        />
      </Box>
      <Button onClick={() => handleBackToCards()} className={classes.button}>Finish Session</Button>
    </div>
  )
}
      
export default Session