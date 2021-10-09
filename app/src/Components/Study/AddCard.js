/**
 * Add Card POP UP
 * @author Pamela Filipinski
 */
import {
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from '@material-ui/core'
    
import React from 'react'
import handleCatchError from '../_helpers/handleCatchServicesError'
import isAxiosError from '../_helpers/isAxiosError'
import CardService from '../../services/cards'
    
const AddCard = (props) => {
  const { open, card, setCard,  group, setOpen,  updateList, setUpdateList , setAlert} = props
    
  /**
   * Handle close dialog
   * @author Pamela Filipinski
   */
  const handleClose = () => {
    setOpen(false)
  }
    
  /**
   * Handle change card frontText input
   * @param {*} e 
   * @author Pamela Filipinski
   */
  const handleChangeFrontText =(e) =>{
    const newNameValue = e.target.value
    setCard({ ...card, frontText: newNameValue })
  }
  /**
   * Handle change card frontText input
   * @param {*} e 
   * @author Pamela Filipinski
   */
  const handleChangeBackText =(e) =>{
    const newNameValue = e.target.value
    setCard({ ...card, backText: newNameValue })
  }
  /**
   * Handle add card-> save button
   * @author Pamela Filipinski
   */
  const addCard = () => {
    CardService.addCard(card)
      .then(result => {
        const isError = isAxiosError(result, setAlert)
        if(isError) return 
        setUpdateList(!updateList)
      })
      .catch(error  => {
        handleCatchError(error, setAlert)
      })
    
    setCard( { frontText: '', backText:'', isSolved:false,    group:{id: group.id},  rightAnswer: false })
    handleClose()
  }
    
  return (
    <div>
      <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Add Card!</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Question"
            fullWidth
            onChange={(e) => handleChangeFrontText(e)}
          />
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Answer"
            fullWidth
            onChange={(e) => handleChangeBackText(e)}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
                            Cancel
          </Button>
          <Button onClick={addCard} color="primary">
                            Save
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}
    
export default AddCard