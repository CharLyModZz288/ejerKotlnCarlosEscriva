package com.example.kotlincompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kotlincompose.usuarios.Usuario
import com.example.kotlincompose.usuarios.MiBDOpenHelper
import com.example.kotlincompose.MainActivity
import com.example.kotlincompose.R
import com.example.kotlincompose.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var productosDB: MiBDOpenHelper

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productosDB = MiBDOpenHelper(requireContext(), null)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.siguiente.setOnClickListener {
            val nombre = binding.usuario2.text.toString()
            val contrasena = binding.contrasena3.text.toString()
            //comprobrar que el usuario esta en la bd
            val usuario = Usuario(0, nombre, contrasena)
            if(productosDB.comprobarUsuario(usuario)==true){
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}