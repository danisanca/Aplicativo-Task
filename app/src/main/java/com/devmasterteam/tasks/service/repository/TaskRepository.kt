package com.devmasterteam.tasks.service.repository


import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository(context: Context) : BaseRepository(context) {
    private val remote = RetrofitClient.getService(TaskService::class.java)

    fun list(listener: APIListener<List<TaskModel>>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.list(),listener)
    }

    fun listNext(listener: APIListener<List<TaskModel>>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.listNext(),listener)
    }

    fun listOverdue(listener: APIListener<List<TaskModel>>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.listOverdue(),listener)
    }



    fun create(task: TaskModel, listener: APIListener<Boolean>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.create(task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call,listener)
    }
    fun update(task: TaskModel, listener: APIListener<Boolean>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.update(task.id,task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call,listener)
    }

    fun load(id: Int, listener: APIListener<TaskModel>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.load(id), listener)
    }


    fun delete(id: Int, listener: APIListener<Boolean>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.delete(id)
        executeCall(call, listener)
    }
    fun complete(id: Int, listener: APIListener<Boolean>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.complete(id)
        executeCall(call, listener)
    }
    fun undo(id: Int, listener: APIListener<Boolean>) {
        if(!isConnectionAvaliable()){
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.undo(id)
        executeCall(call, listener)
    }

}