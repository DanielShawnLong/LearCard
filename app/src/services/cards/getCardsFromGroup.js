/**
 * Service -> get cards from group
 * @author Pamela Filipinski
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
  
export default async function getCardsFromGroup(id) {
  try {
    const path = `/cardsByGroup/${id}`
    const responseData = await axios.get(path,authHeader())
    return  responseData  
  } catch (error) {
    return error
  }
    
}