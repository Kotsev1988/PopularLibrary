package com.example.popularlibrary.view.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrary.App
import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.data.net.GitUsersAPIClient
import com.example.popularlibrary.databinding.FragmentUsersBinding
import com.example.popularlibrary.view.AndroidScreens
import com.example.popularlibrary.view.users.presenter.UsersPresenter
import com.example.popularlibrary.view.BackButtonListener
import com.example.popularlibrary.view.users.adapter.UsersAdapter
import com.example.popularlibrary.view.users.loadImage.GlideImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class UsersFragment() : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GitUsersRepoImpl(
            GitUsersAPIClient()), AndroidSchedulers.mainThread(), App.instance.router, AndroidScreens())
    }
    private var adapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        @JvmStatic
        fun newInstance() = UsersFragment()
    }

    override fun init() {
        binding.usersRecycler.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(presenter.listPresenter, GlideImageLoader())
        binding.usersRecycler.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onError(t: Throwable) {
        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyView()
        _binding = null
    }
}