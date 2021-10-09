/**
 * Service -> update group Level
 * @author Pamela Filipinski
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
  
export default async function updateLevel(group,id) {
  try {
    const path =  `/groups/${id}`//`/cardsAnswer/${id}`
    const responseData = await axios.put(path, group, authHeader())
    return responseData
  } catch (error) {
    return error
  }
   
}