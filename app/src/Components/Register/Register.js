/**
 * Register view 
 * @author Pamela Filipinski
 */
import {
  Grid,
  makeStyles,
  TextField,
  Button,
  ThemeProvider
} from '@material-ui/core'
import registerImg from '../img/register.svg'
import React, { useState } from 'react'
import { addUser } from '../../services/user'
import { createMuiTheme } from '@material-ui/core/styles'
import {REGISTER, NOTIFICATIONS, UNHANDLED_ERROR} from '../../constants'
import isAxiosError from '../_helpers/isAxiosError'
import handleCatchError from '../_helpers/handleCatchServicesError'

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
    marginTop: '200px',
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
    marginTop: '100px',
  },
}))

const Register = (props) => {

  const {setView, setAlert} = props
  const classes = useStyle()
  const [wasSent, setWasSent] = useState(false)
  const [registerData, setRegisterData] = useState({
    userName: '',
    userEmail: '',
    userPassword: '',
    password2: ''
  })
  
  /**
   * On change for username, password, repeat password input
   * @param {*} e 
   * @param {*} controlName 
   * @author Pamela Filipinski
   */
  const onChange = (e, controlName) => {
    e.preventDefault()
    const inputValue = e.target.value
    const data = { ...registerData, [controlName]: inputValue }
    setRegisterData(data)
  }

  /**
   * Handle register button
   * @author Pamela Filipinski
   */
  const handleAddUser = () => {
    if(registerData.userName === '' || registerData.userEmail === '' || registerData.userPassword === '' || registerData.password2 ===''){
      setAlert({open: true, message: NOTIFICATIONS.FILL_ALL_FIELDS, severity: 'warning' })
    }else if(registerData.userPassword !== registerData.password2){
      setAlert({open: true, message: REGISTER.PASSWORD_DONT_MATCH, severity: 'warning' })
    } else if(registerData.userName.length < 4 || registerData.userEmail.length< 4  || registerData.userPassword.length < 4 ){
      setAlert({open: true, message: REGISTER.CONTROL_DATA, severity: 'warning' })
    }
    else {
      addUser(registerData)
        .then((result) => {
          const isError = isAxiosError(result, setAlert)
          if (isError) {
            setRegisterData({userPassword:'', password2: ''})
            return
          }
          setView({isLoggedIn: false, registerView: false})
          
        }) 
        .catch((error) =>  {
          handleCatchError(error, setAlert)
        })
    }
  }
  /**
   * Handle move to login view button
   * @author Pamela Filipinski
   */
  const moveToLoginView = () => {
    setView({ isLoggedIn: false, registerView: false })
  }

  return (
    <div className={classes.background}>
      <ThemeProvider theme={theme}>
        <div>
          <Grid container direction="row" className={classes.grid2}>
            <Grid spacing={3} direction="column" className={classes.textFieldsGrid}>
              <Grid item xs={12}>
                <h3>Register now!</h3>
              </Grid>
              <Grid item xs={12}>
                <TextField
                  error={(registerData.userName.length < 4 && wasSent)}
                  helperText={(registerData.userName.length < 4  && wasSent) ? 'At least 4 chars' : ' '}
                  color="primary"
                  id="outlined-required"
                  label="Username"
                  defaultValue=""
                  variant="outlined"
                  className={classes.textFields}
                  onChange={(e) => onChange(e, 'userName')}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  error={(registerData.userEmail.length < 4 && wasSent)}
                  helperText={(registerData.userEmail.length < 4  && wasSent) ? 'At least 4 chars' : ' '}
                  color="primary"
                  id="outlined-required"
                  label="Email"
                  defaultValue=""
                  variant="outlined"
                  className={classes.textFields}
                  onChange={(e) => onChange(e, 'userEmail')} />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  error={(registerData.userPassword.length < 4 && wasSent)}
                  helperText={(registerData.userPassword.length < 4  && wasSent) ? 'At least 4 chars' : ' '}
                  color="primary"
                  id="outlined-required"
                  label="Password"
                  defaultValue=""
                  variant="outlined"
                  type="password"
                  className={classes.textFields}
                  onChange={(e) => onChange(e, 'userPassword')} />
              </Grid>
              <Grid item xs={12}>
                <TextField
                
                  color="primary"
                  id="outlined-required"
                  label="Repeat Password"
                  defaultValue=""
                  variant="outlined"
                  type="password"
                  className={classes.textFields}
                  onChange={(e) => onChange(e, 'password2')} />
              </Grid>
              <Grid item xs={12} justify="center">
                <Button
                  variant="contained"
                  className={classes.button}
                  onClick={(e) => {
                    handleAddUser()
                    setWasSent(true)}} >
                  Register
                </Button>
                <Button variant="contained" className={classes.button} onClick={(e) => moveToLoginView()} >
                  Go To Login
                </Button>
              </Grid>
            </Grid>
            <Grid>
              <Grid>
                <img alt="register" src={registerImg} className={classes.img} />
              </Grid>
            </Grid>
          </Grid>
        </div>
      </ThemeProvider>
    </div>
  )
}

export default Register
