/**
 * App -> Register/Login/MainApp
 */
import React, { useState } from 'react'
import {
  useHistory,
} from 'react-router-dom'

import Register from './Register/Register'
import Login from './Login/Login'
import MainApp from './MainApp/MainApp'
import Alerts from './Snackbar/Snackbar'

const App = () => {
  const loggedIn = localStorage.getItem('loggedIn')
  const history = useHistory()
  const date = new Date().toLocaleString('en-US', {timeZone: 'Europe/Berlin'})

  const [view, setView] = useState({ isLoggedIn: loggedIn, registerView: false })
  const [alert, setAlert] = useState({open: false, message: 'Alert:',
    vertical: 'top' ,horizontal: 'right' ,autoHideDuration: 2000, severity: '' }) 

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return
    }

    setAlert({...alert, open: false})
  }
  
  if (view.isLoggedIn && !view.registerView) {
    return (
      <div>
        <MainApp setView={setView}  setAlert={setAlert}/>
        <Alerts options={alert} handleClose={handleClose} />
      </div>

    )
  } else if (!view.isLoggedIn && !view.registerView) {
    return (
     
      <div>
        <Login setView={setView} history={history} setAlert={setAlert}/>
        <Alerts options={alert} handleClose={handleClose} />
     
      </div>
    )
  }
  else {
    return (
      <div>
        <Register setView={setView} setAlert={setAlert}/>
        <Alerts options={alert} handleClose={handleClose} />
      </div>
    )
  }
}

export default App
