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
import GroupService from '../../services/group'
  
const AddGroup = (props) => {
  const { open,  group, setOpen, setGroup, updateList, setUpdateList , setAlert} = props
  
  /**
     * Handle close dialog
     */
  const handleClose = () => {
    setOpen(false)
  }
  
  /**
     * Handle change group name input
     * @param {*} e 
     */
  const handleChangeName =(e) =>{
    const newNameValue = e.target.value
    setGroup({ ...group, groupName: newNameValue })
  }
  
  /**
     * Handle add group -> save button
     */
  const addGroup = () => {
  
    GroupService.addGroup(group)
      .then(result => {
        const isError = isAxiosError(result, setAlert)
        if(isError) return 
        setUpdateList(!updateList)
      })
      .catch(error  => {
        handleCatchError(error, setAlert)
      })
  
    setGroup({ groupName:''})
    handleClose()
  }
  
  return (
    <div>
      <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Add group!</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="name"
            label="Group Name"
            fullWidth
            onChange={(e) => handleChangeName(e)}
          />
         
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
                          Cancel
          </Button>
          <Button onClick={addGroup} color="primary">
                          Save
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  )
}
  
export default AddGroup