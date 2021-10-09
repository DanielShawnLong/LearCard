/**
 * Service -> add user (register)
 * @author Pamela Filipinski
 */
import axios from 'axios'

export default async function addUser(user) {
  
  try {
    delete user.password2

    const responseData = await axios.post('/register', user)
    localStorage.setItem('token', responseData.data.token)
    return  responseData.data 
  } catch (error) {
    return error
  }
  
}