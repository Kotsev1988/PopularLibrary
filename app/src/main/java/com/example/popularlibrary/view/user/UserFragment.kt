package com.example.popularlibrary.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.popularlibrary.App
import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.data.net.GitUsersAPIClient
import com.example.popularlibrary.databinding.FragmentUserBinding
import com.example.popularlibrary.view.user.presenter.UserPresenter
import com.example.popularlibrary.view.BackButtonListener
import com.example.popularlibrary.view.user.user_repos.RepoAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), ProfileView, BackButtonListener {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private var repoAdapter: RepoAdapter? = null

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GitUsersRepoImpl(
            GitUsersAPIClient()), App.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var loginUser: String

        arguments?.let {
            loginUser = it.getString("login").toString()
            presenter.loadData(loginUser)
            presenter.loadRepoData(loginUser)

        }
    }

    override fun init() {

        binding.repoRecycler.layoutManager = LinearLayoutManager(context)
        repoAdapter = RepoAdapter(presenter.repoListPresenter)
        binding.repoRecycler.adapter = repoAdapter

    }

    override fun setName(text: String) {
        binding.userProfileLogin.text = text
    }

    override fun setAvatar(url: String) {

        binding.userAvatar.load(url)
    }

    override fun onError(e: Throwable) {
        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
    }

    override fun updateList() {
        repoAdapter?.notifyDataSetChanged()
    }

    override fun setRepoDateOnClick(date: String) {
        binding.createRpoAt.text = date
    }

    companion object {

        @JvmStatic
        fun newInstance(login: String): UserFragment {
            val fragment = UserFragment()
            val arg = Bundle()
            arg.putString("login", login)
            fragment.arguments = arg
            return fragment
        }

    }

    override fun backPressed(): Boolean = presenter.backPressed()


}