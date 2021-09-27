/**
 * Service -> login user
 */
import axios from 'axios'

export default async function loginUser(user) {

  try {
    const responseData = await axios.post('/authenticate', user)
    localStorage.setItem('token', responseData.data.token)
    localStorage.setItem('name', responseData.data.name)
    localStorage.setItem('loggedIn', true)
    return responseData.data
  } catch (error) {
    
    return error
  }

}
