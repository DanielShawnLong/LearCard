/**
 * Login View
 * @author Pamela Filipinski
 */

import React, { useState } from 'react'
import {
  Grid,
  makeStyles,
  TextField,
  Button,
  ThemeProvider
} from '@material-ui/core'
import loginImg from '../img/login.svg'
import { loginUser } from '../../services/user'
import { createMuiTheme } from '@material-ui/core/styles'
import isAxiosError from '../_helpers/isAxiosError'
import handleCatchError from '../_helpers/handleCatchServicesError'
import {NOTIFICATIONS} from '../../constants'

const theme = createMuiTheme({
  palette: {
    primary: { main: '#00b5c2' },
  },
})

const useStyle = makeStyles(() => ({
  root: {
    flexGrow: 1,
    position: 'sticky',
    zIndex: 50,
  },
  textFieldsGrid: {
    marginLeft: '500px',
    marginTop: '100px',
  },
  textFields: {
    color: '#eb52b5',
    marginTop: '5px',
  },
  button: {
    background: 'linear-gradient(50deg, #5be8f5 30%, #00b5c2 90%)',
    marginTop: '5px',
    marginLeft: '5px'
  },
  img: {
    height: '500px',
    width: '500px',
    marginTop: '1px',
  },
  title: {
    color: 'black',
    marginLeft: '650px',
    fontFamily: 'Roboto',
    fontSize: 20,
    flexGrow: 1,
    marginTop: '100px',
  },
}))

const Login = (props) => {

  const { setView, setAlert } = props

  const classes = useStyle()
  const [wasSent, setWasSent] = useState(false)
  const [loginData, setLoginData] = useState({
    userName: '',
    userPassword: '',
  })

  /**
   * On change for username, password input
   * @param {*} e 
   * @param {*} controlName 
   * @author Pamela Filipinski
   */
  const onChange = (e, controlName) => {
    e.preventDefault()
    const inputValue = e.target.value
    const data = { ...loginData, [controlName]: inputValue }
    setLoginData(data)
  
  }

  /**
   * Handle login button
   * @author Pamela Filipinski
   */
  const handleLoginUser = () => {
    if (loginData.userName === '' || loginData.userPassword === '') {
      setAlert({open: true, message: NOTIFICATIONS.FILL_ALL_FIELDS, severity: 'warning' })
    } else {
      loginUser(loginData)
        .then((result) => {
          const isError = isAxiosError(result, setAlert)
          if(isError) return
          setWasSent(true)
          setView({ isLoggedIn: true, registerView: false })
        })
        .catch(error => {
          handleCatchError(error, setAlert)
        })
    }
  }

  /**
   * Handle move to register view button
   * @author Pamela Filipinski
   */
  const moveToRegisterView = () => {
    setView({ isLoggedIn: false, registerView: true })
  }

  return (
    <div>
      <ThemeProvider theme={theme}>
        <div className={classes.title}>
          <h1>Learn Cards Ariadne</h1>
        </div>
        <div>
          <Grid container direction="row" className={classes.grid2}>
            <Grid spacing={3} direction="column" className={classes.textFieldsGrid}>
              <Grid item xs={12}>
                <h3>Login now!</h3>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  error={(loginData.userName.length < 4 && wasSent)}
                  helperText={(loginData.userName.length < 4  && wasSent) ? 'At least 4 chars' : ' '}
                  id="outlined-required"
                  label="Username"
                  placeholder="Username"
                  defaultValue=""
                  variant="outlined"
                  className={classes.textFields}
                  onChange={(e) => onChange(e, 'userName')}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  error={loginData.userPassword.length < 4 &&  wasSent}
                  helperText={loginData.userPassword.length < 4 && wasSent? 'At least 4 chars' : ' '}
                  type="password" 

                  id="outlined-required"
                  label="Password"
                  defaultValue=""
                  variant="outlined"
                  placeholder="password"
                  className={classes.textFields}
                  onChange={(e) => onChange(e, 'userPassword')} />
              </Grid>
              <Grid item xs={12} justify="center">
                <Button
                  variant="contained"
                  className={classes.button}
                  onClick={(e) => {
                    handleLoginUser()
                    setWasSent(true)}} >
                Login

                </Button>
                <Button variant="contained" className={classes.button} onClick={(e) => moveToRegisterView()} >
                  Go To Register
                </Button>
              </Grid>
            </Grid>
            <Grid>
              <Grid>
                <img alt="login" src={loginImg} className={classes.img} />
              </Grid>
            </Grid> 
          </Grid>
        </div>
      </ThemeProvider>
    </div>
  )
}

export default Login
