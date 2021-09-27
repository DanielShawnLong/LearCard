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
  const {cardList, group} = props
  const theme = useTheme()
  const sessionLabel = `Session ${group.level}`
  const [activeStep, setActiveStep] = useState(0)
  const[toggleAnswer, setToggleAnswer] = useState(false)
  const classes = useStyle()
  const maxSteps = cardList.cardList.length
  console.log(cardList.cardList)
    
  const handleRight = () => {
    if (activeStep === maxSteps -1) {
      alert('Session Finished!')
    } else {
      setActiveStep((prevActiveStep) => prevActiveStep + 1)
    }
  }
    
  const handleWrong = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1)
  }
  const handleAnswer = () => {
    setToggleAnswer(prevToggle => !prevToggle)
  }
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
          <Button onClick={() => handleAnswer()} className={classes.button}>{toggleAnswer?'Show Question':'Show Answer'}</Button>
         
        </Box>
        <MobileStepper
          variant="text"
          steps={maxSteps}
          position="static"
          activeStep={activeStep}
          nextButton={
            <Button
              size="small"
              onClick={handleRight}
              disabled={false}
            >
            Right
            
            </Button>
          }
          backButton={
            <Button size="small" onClick={handleWrong} disabled={false}>
           
            Wrong
            </Button>
          }
        />
      </Box>
    </div>
  )
}
      
export default Session