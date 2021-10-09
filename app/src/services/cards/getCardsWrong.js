/**
 * Service -> get wrong answered cards from group
 * @author Pamela Filipinski
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
   
export default async function getCardsWrong(id) {
  try {
    const path = `/cards-wrong/${id}`
    const responseData = await axios.get(path,authHeader())
    return  responseData  
  } catch (error) {
    return error
  }
     
}