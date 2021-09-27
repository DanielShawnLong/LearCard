/**
 * Main App Component -> Header/Sidebar/Calendar/Footer
 */
import React from 'react'
import {
  Grid,
  makeStyles,
  Typography,
  Button
} from '@material-ui/core'
import Header from '../Header/Header'
import Groups from '../Study/Groups'

const useStyle = makeStyles(() => ({
  root: {
    flexGrow: 1,
    position: 'sticky',
    zIndex: 50,
  },
  textFieldsGrid: {
    marginLeft: '250px',
    marginTop: '50px',
  },
  textFields: {
    color: '#eb52b5',
    marginTop: '5px',
  },
  button: {
    backgroundColor: '#eb52b5',
    marginTop: '5px',
    marginLeft: '5px'
  },
  gridFooter: {
    background: 'linear-gradient(50deg, #5be8f5 30%, #00b5c2 90%)',
    marginTop: '114px',
    bottom: 10,
    width: '100%',
    height: '30px'
  },
  group: {
  
    background: '#fffff',
    width: '100%',
    height: '1000%'
  },
  img: {
    height: '300px',
    width: '300px',
    padding: '20px',
  },
  textTitle: {
    color: 'black',
    marginLeft: '50px',
    fontFamily: 'Roboto',
    fontSize: 30,
    flexGrow: 1
  },
  back: {
    background: '#96ffff'
  }
}))

const MainApp = (props) => {
  const classes = useStyle()
  const { setView, setAlert } = props
  const loggedIn = localStorage.getItem('loggedIn')
  if (!loggedIn) {

    window.location.href = './'
    return (<div></div>)
  } else {
    return (
      <div className={classes.back}>
        <Grid container>
          <Grid item xs={12}>
            <Header setView={setView} />
          </Grid>
          <div className={classes.group}>
            <Groups></Groups>
          </div>
          <Grid item xs={12} className={classes.gridFooter}>
            <Typography variant="caption" display="block" gutterBottom>
              Images: undraw
            </Typography>
          </Grid>
        </Grid>
      </div>
    )
  }
}

export default MainApp