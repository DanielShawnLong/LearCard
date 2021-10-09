/**
 * Service -> delete card
 * @author Pamela Filipinski
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
  
export default async function deleteCard(id) {
  try {
    const path = `/cards/${id}`
    const responseData = await axios.delete(path, authHeader())
    return responseData
  } catch (error) {
    return error
  }
    
}