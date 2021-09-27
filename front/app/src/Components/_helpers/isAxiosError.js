export default (result, setAlert) => {
  if (result.isAxiosError){
    setAlert({open: true, message: result.response.data.error.text, severity: 'error'})
    return true // result to handle error in useEffect
  }
}