package com.example.kotlincompose

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.kotlincompose.usuarios.MiBDOpenHelper
import com.example.kotlincompose.usuarios.Usuario
import com.example.kotlincompose.R
import com.example.kotlincompose.databinding.FragmentRegisterBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var productosDB: MiBDOpenHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productosDB = MiBDOpenHelper(requireContext(), null)
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.siguiente2.setOnClickListener {
            val nombre = binding.usuario.text.toString()
            val contrasena = binding.contrasena.text.toString()
            val contrasena2 = binding.contrasena2.text.toString()
            if(contrasena==contrasena2){
                //añadir el usuario a la bd
                val usuario = Usuario(0, nombre, contrasena)
                productosDB.anadirUsuario(usuario)
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(context, "Las contraseñas son diferentes", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}