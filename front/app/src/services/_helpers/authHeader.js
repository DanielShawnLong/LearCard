/**
 * Get Bearer token from localstorage
 * @returns 
 */
const authHeader = () => {
  let token = localStorage.getItem('token')
  if (!token) setTimeout(() => {
    token = localStorage.getItem('token')
  }, 2000)
  return {
    headers: {
      Authorization: 'Bearer ' + token
    }
  }
}

export default authHeader