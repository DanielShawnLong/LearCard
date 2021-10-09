/**
 * Service -> delete group
 * @author Pamela Filipinski
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
 
export default async function deleteGroup(id) {
  try {
    const path = `/groups/${id}`
    const responseData = await axios.delete(path, authHeader())
    return responseData
  } catch (error) {
    return error
  }
   
}