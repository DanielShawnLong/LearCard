/**
 * Service -> get groups
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
 
export default async function getGroups() {
  try {
    const path = '/groups'
    const responseData = await axios.get(path,authHeader())
    return  responseData  
  } catch (error) {
    return error
  }
   
}