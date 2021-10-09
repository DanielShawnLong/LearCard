/**
 * Service -> update card answer
 * @author Pamela Filipinski
 */
import axios from 'axios'
import authHeader from '../_helpers/authHeader'
 
export default async function updateCard(card,id) {
  try {
    const path = `/cardsAnswer/${id}`
    const responseData = await axios.put(path, card, authHeader())
    return responseData
  } catch (error) {
    return error
  }
  
}