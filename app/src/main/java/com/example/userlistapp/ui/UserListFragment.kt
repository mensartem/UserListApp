package com.example.userlistapp.ui


import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.userlistapp.R
import com.example.userlistapp.base.BaseFragment
import com.example.userlistapp.databinding.FragmentUserListBinding
import com.example.userlistapp.util.Status
import com.example.userlistapp.util.extension.gone
import com.example.userlistapp.util.extension.isNetworkAvailable
import com.example.userlistapp.util.extension.visible


class UserListFragment :
    BaseFragment<UserListViewModel, FragmentUserListBinding>(UserListViewModel::class) {

    override val getLayoutId: Int
        get() = R.layout.fragment_user_list


    private lateinit var userListAdapter: UserListAdapter


    override fun initialize() {


        fetchList()
        subscribeViewModel()
        userListAdapter = UserListAdapter()
        binding.rcvUsers.apply {
            adapter = userListAdapter
            hasFixedSize()
        }

        binding.swpRefresh.setOnRefreshListener {
            binding.rcvUsers.gone()
            fetchList(resetList = true)
        }

        binding.ivRefresh.setOnClickListener {
            fetchList()
        }

        binding.rcvUsers.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fetchList()
                }
            }
        })
    }

    private fun subscribeViewModel() {
        viewModel.userList.observe(viewLifecycleOwner, Observer { userList ->

            binding.rcvUsers.isVisible = !userList.isNullOrEmpty()

            if (userList.isNullOrEmpty()) {
                binding.clMain.onError(getString(R.string.no_list))
            } else {
                binding.rcvUsers.visible()
                userListAdapter.submitList(userList)
                userListAdapter.notifyDataSetChanged()
                binding.clMain.onSuccess()

            }

        })

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            binding.viewState = status
            binding.swpRefresh.isRefreshing = false

            if(status.isError()) binding.clMain.onInternalError()

        })
    }

    private fun fetchList(resetList: Boolean = false) {

        if (!requireContext().isNetworkAvailable()) {
            viewModel.setStatus(UserListStatusViewState(Status.Error(Throwable(getString(R.string.no_connection)))))
        } else {
            binding.clMain.onLoading()
            viewModel.fetchUserList(resetList)
        }

    }
}
