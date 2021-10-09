/**
 * Service -> add group
 * @author Pamela Filipinski
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
export default async function addGroup(group) {
  try {
    const responseData = await axios.post('/groups',group,authHeader())
    return  responseData.data
  } catch (error) {
    return error
  }
 
}