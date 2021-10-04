/**
 * Service -> get cards from group
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
   
export default async function getCardsNotSolved(id) {
  try {
    const path = `/cards-session/${id}`
    const responseData = await axios.get(path,authHeader())
    return  responseData  
  } catch (error) {
    return error
  }
     
}