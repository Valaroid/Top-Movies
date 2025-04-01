package com.example.topmovies.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.topmovies.databinding.FragmentRegisterBinding
import com.example.topmovies.model.register.BodyRegister
import com.example.topmovies.util.UserDataStore
import com.example.topmovies.util.viewVisibility
import com.example.topmovies.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentRegisterBinding

    //viewModel
    private val viewModel: RegisterViewModel by viewModels()

    //DataStore
    @Inject
    lateinit var userDataStore: UserDataStore

    @Inject
    lateinit var body:BodyRegister

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init views
        binding.apply {

            //click
            btnSubmitRegister.setOnClickListener {
                val name = txtNameRegister.text.toString()
                val email = txtEmailRegister.text.toString()
                val passWord = txtPassWordRegister.text.toString()

                if (name.isNotEmpty() && email.isNotEmpty() && passWord.isNotEmpty()){

                    body.name=name
                    body.email=email
                    body.password=passWord

                    viewModel.getRegisterUser(body){
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }

                }else{

                    Toast.makeText(requireContext(), "Please Fill The Blank Box", Toast.LENGTH_SHORT).show()
                }


            }

            //loading
            viewModel.isLoading.observe(viewLifecycleOwner){
                if (it){
                    loadingRegister.viewVisibility(true)
                    btnSubmitRegister.viewVisibility(false)

                }else{
                    loadingRegister.viewVisibility(false)
                    btnSubmitRegister.viewVisibility(true)

                }

            }



            //token
            viewModel.registerUser.observe(viewLifecycleOwner){

                lifecycleScope.launch {
                    userDataStore.saveTokenToDataStore(it.name.toString())
                    Log.e("thread",Thread.currentThread().name.toString())
                }



            }


        }


    }


}