/**
 * Service -> reset cards
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
  
export default async function resetCards(id) {
  try {
    const path = `/resetCards/${id}`
    const responseData = await axios.put(path,{}, authHeader())
    return responseData
  } catch (error) {
    return error
  }
   
}