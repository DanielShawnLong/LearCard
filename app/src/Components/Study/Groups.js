/**
 * Header Component
 */
import React, { useState, useEffect } from 'react'
import {
  makeStyles,
  Typography,
  Button,
  Grid,
  Tooltip,
  List,
  IconButton,
  ListItemText
} from '@material-ui/core'
import DeleteIcon from '@material-ui/icons/Delete'
import GroupService from '../../services/group'
import handleCatchError from '../_helpers/handleCatchServicesError'
import isAxiosError from '../_helpers/isAxiosError'
import AddGroup from './AddGroup'
import Cards from './Cards'

const useStyle = makeStyles(() => ({
  button: {
    background: '#00b5c2',
    marginTop: '5px',
    marginLeft: '5px',
    color: 'black',
    fontFamily: 'Roboto',
  },
  button2: {
    background: '#00b5c2',
    marginTop: '15px',
    marginBottom: '15px',
    color: 'black',
    fontFamily: 'Roboto',
  },
  textTitle: {
    color: 'black',
    marginLeft: '50px',
    fontFamily: 'Roboto',
    fontSize: 30,
    flexGrow: 1
  },
  msg: {
    color: 'black',
    marginLeft: '50px',
    fontFamily: 'Roboto',
    fontSize: 20,
    flexGrow: 1
  },
  group: {
    background: '#5be8f5 ', 
  }

}))
 
const Groups = (props) => {
  const { setAlert } = props
  
  const [group, setGroup] = useState({ groupName: '' })
  const [groupList, setGroupList] = useState({ groupList: null })
  const [open, setOpen] = useState(false)
  const [index, setIndex] = useState(false)
  const [openCards, setOpenCards] = useState(false)
  const [updateList, setUpdateList] = useState(false)
  const classes = useStyle()

  let sortedGroups

  /**
   * Get data from API when rendered or when change
   */
  useEffect(() => {

    GroupService.getGroups()
      .then(
        (groupList) => {
          const isError =  isAxiosError(groupList,setAlert)
          if (isError) return
          sortedGroups = groupList.data.sort((a, b) => a.id - b.id)
          setGroupList({ groupList: sortedGroups })
        }
      ).catch(error => {
        handleCatchError(error, setAlert)
      })

  }, [updateList])
 
  /**
   * Handle open add group dialog 
   */
  const handleClickOpen = () => {
    setOpen(true)
  }
  /**
   * Handle open cards
   */
  const handleOpenCards = (index) => {
    setIndex(index)
    setOpenCards(true)
  }
  /**
 * Handle delete group
 * @param {*} id 
 */
  const handleDelete = (id) => {
    
    GroupService.deleteGroup(id)
      .then(result => console.log(result))
      .catch(error => alert('etwas ist schiefgelaufen DELETE'))
    setUpdateList(!updateList)
  }
  if (!groupList.groupList || groupList.groupList.length === 0)
    return<div> <Grid container xs={5}>
      <Typography className={classes.textTitle}>
     GROUPS
        <Button onClick={() => handleClickOpen()} className={classes.button}>Add</Button>
      </Typography>
      <AddGroup
        setAlert={setAlert}
        open={open}
        setOpen={setOpen}
        group={group}
        setGroup={setGroup}
        updateList={updateList}
        setUpdateList={setUpdateList}
      />
      <Typography className={classes.msg}>You dont have any groups yet! Add new and start learning today!</Typography>
    </Grid></div>
  
  if (openCards)
    return (
      <div>
        <Cards
          group={groupList.groupList[index]}
          openCards={openCards}
          setOpenCards={setOpenCards}
          setAlert={setAlert}>
        </Cards>
      </div>
    )
  return (
    <div>
      <Grid container direction="column">
        <Grid item direction="row">
          <Typography className={classes.textTitle}>
         GROUPS
            <Button onClick={() => handleClickOpen()} className={classes.button}>Add group</Button>
          </Typography>
          <AddGroup
            setAlert={setAlert}
            open={open}
            setOpen={setOpen}
            group={group}
            setGroup={setGroup}
            updateList={updateList}
            setUpdateList={setUpdateList}
          />
        </Grid>
        <Grid item direction="row">
          <List >
            { groupList.groupList.map((group, index) => {
              return (
                <div key={group.id} className={classes.group} >
             
                  <ListItemText className={classes.msg}>
                     &ldquo;{group.groupName}&ldquo;
                    
                    <Tooltip title="Delete">
                      <IconButton color="primary" onClick={() => handleDelete(group.id)}>
                        <DeleteIcon
                          fontSize="small"
                        />
                      </IconButton>
                
                    </Tooltip>
                    <Button onClick={() => handleOpenCards(index)} className={classes.button2}>CARDS</Button>
                  </ListItemText>
                 
                  <div className={classes.dividerSmall} />
                </div>
              )
            })}
          </List>
        </Grid>
      </Grid>
  
    </div>
  )
}
 
export default Groups