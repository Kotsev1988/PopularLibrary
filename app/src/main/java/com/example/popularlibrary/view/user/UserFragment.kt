package com.example.popularlibrary.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrary.App
import com.example.popularlibrary.data.GitHubUsersRepoImpl
import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.data.net.GitUsersAPIClient
import com.example.popularlibrary.data.room.Database
import com.example.popularlibrary.databinding.FragmentUserBinding
import com.example.popularlibrary.domain.users.UsersItem
import com.example.popularlibrary.view.user.presenter.UserPresenter
import com.example.popularlibrary.view.BackButtonListener
import com.example.popularlibrary.view.network.NetworkStatusImpl
import com.example.popularlibrary.view.user.loadUserAvatar.LoadUserAvatar
import com.example.popularlibrary.view.user.user_repos.RepoAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), ProfileView, BackButtonListener {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private var repoAdapter: RepoAdapter? = null
    private val loadUserAvatar = LoadUserAvatar()

    private val presenter: UserPresenter by moxyPresenter {
        var user =  arguments?.getParcelable<UsersItem>("user") as UsersItem
        UserPresenter(user, GitHubUsersRepoImpl(GitUsersAPIClient(), NetworkStatusImpl(App.instance),
            Database.getInstance()), AndroidSchedulers.mainThread(), App.instance.router)
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
       presenter.loadData()
        presenter.loadRepoData()
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
        loadUserAvatar.loadUserImage(url, binding.userAvatar)
    }

    override fun onError(e: Throwable) {
        Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
    }

    override fun updateList() {
        repoAdapter?.notifyDataSetChanged()
    }

    override fun setRepoDateOnClick(date: String, forks: Int) {
        binding.createRpoAtText.visibility = View.VISIBLE
        binding.createRpoAt.text = date
        binding.forksCount.visibility = View.VISIBLE
        binding.countOfForks.text = forks.toString()
    }

    companion object {

        @JvmStatic
        fun newInstance(user: UsersItem): UserFragment {
            val fragment = UserFragment()
            val arg = Bundle()
            arg.putParcelable("user", user)
            fragment.arguments = arg
            return fragment
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}