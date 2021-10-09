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
import Box from '@mui/material/Box'
import { useTheme } from '@mui/material/styles'
import MobileStepper from '@mui/material/MobileStepper'
import Paper from '@mui/material/Paper'
import KeyboardArrowLeft from '@mui/icons-material/KeyboardArrowLeft'
import KeyboardArrowRight from '@mui/icons-material/KeyboardArrowRight'
import DeleteIcon from '@material-ui/icons/Delete'
import React, {useEffect, useState, useReducer }from 'react'
import handleCatchError from '../_helpers/handleCatchServicesError'
import isAxiosError from '../_helpers/isAxiosError'
import CardService from '../../services/cards'
import GroupService from '../../services/group'
import { SettingsInputAntennaTwoTone } from '@material-ui/icons'

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

const Session = (props) => {
  const {cardList, group, setGroup, setOpenSession, openSession, setCardList, updateList, setAlert, setUpdateList, setCard} = props
  const theme = useTheme()
  const sessionLabel = `Level ${group.level}`
  const [activeStep, setActiveStep] = useState(0)
  const [toggleAnswer, setToggleAnswer] = useState(true)
  const [nextSession, setNextSession] = useState(false) //for next level
  const [emptyList, setEmptyList] = useState(false)
  const[update, setUpdate]=   useState(false)
  const classes = useStyle()
 
  var maxSteps = cardList.cardList.length
  let sortedCards
  const forceUpdate = React.useReducer(bool => !bool)[1]
 
  const handleRight = () => {
    
    console.log('TRUE??1', cardList.cardList.filter((card) => card['rightAnswer'] == true))
    updateRight(cardList.cardList[activeStep].id)
    CardService.updateCardAnswer({ rightAnswer: true, isSolved: true }, cardList.cardList[activeStep].id).then(
      () => {
        if (activeStep === maxSteps - 1) {
     
          //setCardList({ ...cardList, [cardList.cardList[activeStep].rightAnswer]: true })
          //() => setCardList((prev) => ({ ...prev, [cardList.cardList[activeStep].rightAnswer]: true }))
          //updateRight(cardList.cardList[activeStep].id)
          //console.log('CARDLIST', cardList.cardList[activeStep])
          //forceUpdate()
          //setUpdateList(!updateList)
          
          console.log('TRUE??', cardList.cardList.filter((card) => card['rightAnswer'] == false).length)
          //!cardList.cardList.some((card) => card['rightAnswer'] === false) || 
          if (cardList.cardList.filter((card) => card['rightAnswer'] == false).length <= 1) {
          //if(cardList.cardList !== null){
            console.log('TRUE', cardList.cardList)
            setEmptyList(true)
          } else {
         
            console.log('FALSE', cardList.cardList)
            handleAnswer()//always show Question first
            setNextSession(true)//When all questions Answered move to next level
          
          }
          //updateRight(cardList.cardList[activeStep].id)
          // handleAnswer()//always show Question first
          // setNextSession(true)//When all questions Answered move to next level
        
        } else {
        
          //setCardList({ ...cardList, [cardList.cardList[activeStep].rightAnswer]: true })
          //() => setCardList((prev) => ({ ...prev, [cardList.cardList[activeStep].rightAnswer]: true }))
          //() => setCardList((prev) => ({ ...prev, [prev.cardList[activeStep].rightAnswer]: true }))
          //updateRight(cardList.cardList[activeStep].id)           //!!!
          // console.log('AKTIV_STEP', activeStep)
          // console.log('CARDLIST 2', cardList.cardList[activeStep])
          //console.log('CARDLIST LENGTH',cardList.cardList.length)
          console.log('Hello', cardList)
          handleAnswer()//always show Question first
          setActiveStep((prevActiveStep) => prevActiveStep + 1)
       
        }
      }
    )
    /*
    //setUpdateList(!updateList)
    if (activeStep === maxSteps - 1) {
     
      //setCardList({ ...cardList, [cardList.cardList[activeStep].rightAnswer]: true })
      //() => setCardList((prev) => ({ ...prev, [cardList.cardList[activeStep].rightAnswer]: true }))
      //updateRight(cardList.cardList[activeStep].id)
      //console.log('CARDLIST', cardList.cardList[activeStep])
      setUpdateList(!setUpdateList)
      console.log('TRUE??', cardList.cardList.some((card) => card['rightAnswer'] == true))
      if (!cardList.cardList.some((card) => card['rightAnswer'] === false) || cardList.cardList == []) {
        
        console.log('TRUE',cardList.cardList)
        setEmptyList(true)
      } else {
       
        console.log('FALSE', cardList.cardList)
        handleAnswer()//always show Question first
        setNextSession(true)//When all questions Answered move to next level
        
      }
      //updateRight(cardList.cardList[activeStep].id)
      // handleAnswer()//always show Question first
      // setNextSession(true)//When all questions Answered move to next level
      
    } else {
      
      //setCardList({ ...cardList, [cardList.cardList[activeStep].rightAnswer]: true })
      //() => setCardList((prev) => ({ ...prev, [cardList.cardList[activeStep].rightAnswer]: true }))
      //() => setCardList((prev) => ({ ...prev, [prev.cardList[activeStep].rightAnswer]: true }))
      //updateRight(cardList.cardList[activeStep].id)           //!!!
      // console.log('AKTIV_STEP', activeStep)
      // console.log('CARDLIST 2', cardList.cardList[activeStep])
      //console.log('CARDLIST LENGTH',cardList.cardList.length)
      console.log('Hello',cardList)
      handleAnswer()//always show Question first
      setActiveStep((prevActiveStep) => prevActiveStep + 1)
     
    }*/
  }
  const updateRight = (id)=>{
    //setCardList(() => (prev) => ({ ...prev, [prev.cardList[activeStep].rightAnswer]: true }))
    const newList = cardList.cardList.map((item) => {
      console.log('ID', item.id)
      if (item.id == id) {
        const updatedItem = {
          ...item, rightAnswer: true,
        }
        console.log('UP ITEM:', updatedItem)
      
        return updatedItem
      }
      console.log('ITEM:', item)
     
      return item
      
    })
    const newObject = { cardList: newList }

    console.log('CARDLIST PAMCIA:', cardList)
    console.log('NEW LIST:', newList)
    console.log('Object PAMCIA:',newObject)
    
    //setCardList(newObject)//{...cardList, cardList: newList})
    //setCardList(['HELLO'])
    // setCardList((prev) => ({ prev, newObject })) // 2 arrays
    
    console.log('CARDLIST OBJECT STATE PAMCIA 1:', cardList)
    //setCardList((prev) => ({ ...prev,  newList })) // 2 arrays
    setCardList((prev) => (  newObject ))
    console.log('CARDLIST OBJECT STATE PAMCIA:', cardList)
    if (!newList.some((card) => card['rightAnswer'] == true)) {
      
      console.log('TRUE')
      setEmptyList(true)
    }
    
  }
  const updateWrong = (id)=>{
    //setCardList(() => (prev) => ({ ...prev, [prev.cardList[activeStep].rightAnswer]: true }))
    const newList = cardList.cardList.map((item) => {
      console.log('ID', item.id)
      if (item.id == id) {
        const updatedItem = {
          ...item, rightAnswer: false,
        }
        console.log('UP ITEM:', updatedItem)
      
        return updatedItem
      }
      console.log('ITEM:', item)
     
      return item
      
    })
    const newObject = { cardList: newList }

    console.log('CARDLIST PAMCIA:', cardList)
    console.log('NEW LIST:', newList)
    console.log('Object PAMCIA:',newObject)
    
    //setCardList(newObject)//{...cardList, cardList: newList})
    //setCardList(['HELLO'])
    // setCardList((prev) => ({ prev, newObject })) // 2 arrays
    
    console.log('CARDLIST OBJECT STATE PAMCIA 1:', cardList)
    //setCardList((prev) => ({ ...prev,  newList })) // 2 arrays
    setCardList((prev) => (  newObject ))
    console.log('CARDLIST OBJECT STATE PAMCIA:', cardList)

  }
  const handleWrong = () => {
    updateWrong(cardList.cardList[activeStep].id)
    CardService.updateCardAnswer({ rightAnswer: false, isSolved: true }, cardList.cardList[activeStep].id)
    // CardService.getCardsFromGroup(group.id)
   
    if (activeStep === maxSteps - 1) {
      //setCardList({ ...cardList, [cardList.cardList[activeStep].rightAnswer]: false })
      
      console.log('CARDLIST 3',cardList.cardList[activeStep])
  
      handleAnswer()//always show Question first
      setNextSession(true) //When all questions Answered move to next level
      //cardList.cardList[activeStep].rightAnswer= false
    } else {
      //setCardList({ ...cardList, [cardList.cardList[activeStep].rightAnswer]: false })
    
      console.log('CARDLIST 4',cardList.cardList[activeStep])
      handleAnswer() //always show Question first
      setActiveStep((prevActiveStep) => prevActiveStep + 1)
      
    }
  }
  const handleAnswer = () => {
    setToggleAnswer(prevToggle => !prevToggle)
  }
    
  const handleBackToCards = () => {
   
    CardService.resetCards(group.id)
    setUpdateList(!updateList)
    group.level = 0
    GroupService.updateLevel({ group }, group.id)
    CardService.getCardsFromGroup(group.id)
    setOpenSession(false)
   
  }
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
    <div>
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
              { console.log('RENDER',cardList)}
            </Button>
          }
          backButton={
            <Button size="small" onClick={() => handleWrong()} disabled={false}>
                  Wrong
                  
            </Button>
          }
        />
      </Box>
      <Button onClick={() => handleBackToCards()} className={classes.button}>Back to Cards</Button>
    </div>
  )
}
      
export default Session