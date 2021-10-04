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
import React, {useEffect, useState }from 'react'
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
  const {cardList, group, setGroup, setOpenSession, openSession, setCardList, updateList} = props
  const theme = useTheme()
  const sessionLabel = `Level ${group.level}`
  const [activeStep, setActiveStep] = useState(0)
  const [toggleAnswer, setToggleAnswer] = useState(true)
  const  [nextSession, setNextSession] =useState(false)
  
  const classes = useStyle()
  //const maxSteps = cardList.cardList.length
 
  var newList = cardList.cardList.filter(() => !cardList.cardList[activeStep].rightAnswer)
  const maxSteps = newList.length
  console.log('NEW', newList)
  const handleRight = () => {

    CardService.updateCardAnswer({ rightAnswer: true, isSolved: true }, cardList.cardList[activeStep].id) 
    !updateList
    if (activeStep === maxSteps -1) {
      !updateList
      handleAnswer()
      setNextSession(true)
    } else {
      handleAnswer()
      setActiveStep((prevActiveStep) => prevActiveStep + 1)
      
    }
  }
    
  const handleWrong = () => {
 
    CardService.updateCardAnswer({ rightAnswer: false, isSolved: true }, cardList.cardList[activeStep].id)
    !updateList
    if (activeStep === maxSteps - 1) {
      !updateList
      handleAnswer()
      setNextSession(true)
      //alert('Session Finished!')
    } else {
      handleAnswer()
      setActiveStep((prevActiveStep) => prevActiveStep + 1)
    }
  }
  const handleAnswer = () => {
    setToggleAnswer(prevToggle => !prevToggle)
  }
    
  const handleBackToCards = () => {
    setOpenSession(false)
  }
  const handleNextLevel = () => {
    let nextLevel = group.level + 1
    group.level = nextLevel
    GroupService.updateLevel({ group }, group.id)
    console.log('Level 2', group.level)
    setNextSession(false)
    setActiveStep(0)
    
  }

  if (nextSession)
    return (<div>
      You finished level: {group.level}
      <Button onClick={() => handleNextLevel()} className={classes.button}>Next Level</Button>
      <Button onClick={() => handleBackToCards()} className={classes.button}>Finish Session</Button>
      <Button onClick={() => handleBackToCards()} className={classes.button}>Save Session</Button>
      
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
          {toggleAnswer ? newList[activeStep]?.frontText : newList[activeStep]?.backText }
          {/*  {toggleAnswer ? cardList.cardList[activeStep]?.frontText : cardList.cardList[activeStep]?.backText } */}
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
      <Button onClick={() => handleBackToCards()} className={classes.button}>Back to Cards</Button>
    </div>
  )
}
      
export default Session