// get handles property verification nicer
import {get} from 'lodash'
import  {UNHANDLED_ERROR,  CONTACT_SUPPORT} from '../../constants'
export default (error,setAlert) => {
  
  if (get(error, 'error.response.data.error.statusCode') && error.isAxiosError) {
    let message =''
    switch (error.response.data.error.statusCode) {
    case 500:
      message = error.response.data.error.text
      break
      // other errors can be handled here
    default:
      message = CONTACT_SUPPORT
      break
    }
    setAlert({open: true, message: message, severity: 'error'})
  } else {
    setAlert({open: true, message: UNHANDLED_ERROR, severity: 'error'})
  }
 
}