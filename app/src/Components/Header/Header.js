/**
 * Header Component
 * @author Pamela Filipinski
 */
import React from 'react'
import {
  makeStyles,
  Button,
  AppBar,
  Toolbar,
  Typography,
} from '@material-ui/core'

import ExitToAppRoundedIcon from '@material-ui/icons/ExitToAppRounded'
import SchoolIcon from '@material-ui/icons/School'

const useStyle = makeStyles(() => ({
  button: {
    background: 'linear-gradient(50deg, #5be8f5 30%, #00b5c2  90%)',
    marginTop: '5px',
    marginLeft: '5px',
    color: 'black',
    fontFamily: 'Roboto',
  },
  appBar: {
    background: 'linear-gradient(50deg, #5be8f5 30%, #00b5c2  90%)',
  },
  textTitle: {
    color: 'black',
    marginLeft: '500px',
    fontFamily: 'Roboto',
    fontSize: 50,
    flexGrow: 1,
  },
  textDate: {
    color: 'black',
    fontFamily: 'Roboto',
  },
  icon: {
    color: 'black',
  },
}))

var today = new Date().toLocaleDateString()

const Header = (props) => {
  const { setView } = props

  /**
   * Handle logout button
   * @author Pamela Filipinski
   */
  const handleOnClick = () => {
    localStorage.clear()
    setView(false)
    window.location.href = 'http://localhost:3000'
  }             
  const classes = useStyle()

  const userName = localStorage.getItem('name')

  return (
    <div>
      <AppBar position="static" className={classes.appBar}>
        <Toolbar>
          <Typography className={classes.textDate}>{today}</Typography>
          <SchoolIcon className={classes.icon} />
          <Typography className={classes.textTitle} >
            Learn Cards Ariadne
          </Typography>
          <Button
            startIcon={<ExitToAppRoundedIcon />}
            className={classes.button}
            onClick={() => handleOnClick()}>
            Logout
          </Button>
        </Toolbar>
      </AppBar>
    </div>
  )
}

export default Header