/**
 * Service -> add card
 * @author Pamela Filipinski
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
export default async function addCard(card) {
  try {
    const responseData = await axios.post('/cards',card,authHeader())
    return  responseData.data
  } catch (error) {
    return error
  }
  
}