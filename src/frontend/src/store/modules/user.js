import { setDate } from "date-fns";

export const state = {

  userName : null,
  userId : null,
  userPanelty : [],
  userShortcutLib : [],
  userEmail : null,

}


export const getters =
{

  getUserName : state => state.userName,
  getUserId : state => state.userId,
  getUserPnelty : state => state.userPanelty,
  getUserLib : state=>state.userShortcutLib,
  getUserEmail : state=>state.userEmail,


}



export const mutations ={


    updateEmail(state,newEmail)
    {
      state.userEmail = newEmail
    },

    updateName(state,newName)
    {
      state.userName = newName;


    }
    ,
    updateId(state,newId)
    {
      state.userId = newId;


    }
    ,

    updatePanelty(state,newPanelty)
    {
      state.userPanelty = newPanelty;


    }
    ,
    addUserLibrary(state,newLib)
    {

      var index = state.userShortcutLib.findIndex(i => i.id==newLib.id)
      if(index == -1)
      {
        state.userShortcutLib.push(newLib)
      }



    },

    delUserLibrary(state,Lib)
    {

      var index = state.userShortcutLib.findIndex(i => i.name==Lib.name && i.area==Lib.area)
      if(index != -1)
      {
        state.userShortcutLib.splice(index,1)
      }

    },

    clearUserLibrary(state)
    {
      state.userShortcutLib = []
    }





}

export const actions ={



}


export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
