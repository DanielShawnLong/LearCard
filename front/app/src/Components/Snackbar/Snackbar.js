/**
 * Snackbar component for error msg
 */
import { Snackbar, makeStyles } from '@material-ui/core'
import React from 'react'
import MuiAlert from '@material-ui/lab/Alert'
import {SNACK_BAR_DEFAULTS} from '../../constants'

const useStyles = makeStyles(()=>({
  snackBar: {marginBottom: '10px'}
}))

const Alerts =  (props) => { 
  const classes = useStyles()
  const {options, handleClose } = props 
  const vertical = options.vertical ? options.vertical : SNACK_BAR_DEFAULTS.VERTICAL
  const horizontal = options.horizontal ? options.horizontal : SNACK_BAR_DEFAULTS.HORIZONTAL
  const autoHideDuration = options.autoHideDuration ? options.autoHideDuration : SNACK_BAR_DEFAULTS.AUTO_HIDE_DURATION
  return ( <Snackbar
    key={Math.random(100)}
    className={classes.snackBar}
    anchorOrigin={{ vertical, horizontal}}
    open={options.open}
    onClose={handleClose}
    autoHideDuration={autoHideDuration}
  >
    <MuiAlert severity={options.severity }>{options.message}</MuiAlert>
  </Snackbar>
  )}

export default Alerts