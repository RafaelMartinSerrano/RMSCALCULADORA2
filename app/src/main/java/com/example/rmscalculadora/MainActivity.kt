package com.example.rmscalculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //Declaro una variable de la clased Operacion//

    private lateinit var operaciones : Operacion

    //Declaramos las variables para los TextView//

    private lateinit var operacionActual: TextView
    private lateinit var historial: TextView
    private lateinit var memoria : TextView
    //Declaramos las variables para los Botones//

    private lateinit var boton0: Button
    private lateinit var boton1: Button
    private lateinit var boton2: Button
    private lateinit var boton3: Button
    private lateinit var boton4: Button
    private lateinit var boton5: Button
    private lateinit var boton6: Button
    private lateinit var boton7: Button
    private lateinit var boton8: Button
    private lateinit var boton9: Button
    private lateinit var botonPunto : Button

    //Declaramos las variables para las operaciones//

    private lateinit var botonMas: Button
    private lateinit var botonX: Button
    private lateinit var botonMenos: Button
    private lateinit var botondivision: Button
    private lateinit var botonIgual: Button

    //Declaramos las variables para la primera fila//

    private lateinit var buttonC : Button
    private lateinit var botonMMas : Button
    private lateinit var botonM : Button
    private lateinit var botonAC : Button

    //Declaramos las variables necesarias para el codigo//

    private var primerNumero : String = "0"
    private var segundoNumero : String = "0"
    private var resultado : String = "0"
    private var operando : String = "0"
    private var comprobarSegundo : Boolean = false
    private var comprobarComa : Boolean = false
    private var numeroMemorizado : Boolean = false
    private lateinit var guardado : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initList()

    }
    private fun initView() {

        //Asignamos los variables a los componentes visuales//

        //Numeros//
        boton0 = findViewById(R.id.boton0)
        boton1 = findViewById(R.id.boton1)
        boton2 = findViewById(R.id.boton2)
        boton3 = findViewById(R.id.boton3)
        boton4 = findViewById(R.id.boton4)
        boton5 = findViewById(R.id.boton5)
        boton6 = findViewById(R.id.boton6)
        boton7 = findViewById(R.id.boton7)
        boton8 = findViewById(R.id.boton8)
        boton9 = findViewById(R.id.boton9)

        //El punto para la coma//

        botonPunto = findViewById(R.id.botonComa)

        //PrimeraFila//
        buttonC = findViewById(R.id.buttonC)
        botonMMas = findViewById(R.id.botonMMas)
        botonM = findViewById(R.id.botonM)
        botonAC = findViewById(R.id.botonAC)


        //Operaciones//
        botonMas = findViewById(R.id.botonMas)
        botonX = findViewById(R.id.botonX)
        botonMenos = findViewById(R.id.botonMenos)
        botondivision = findViewById(R.id.botondivision)
        botonIgual = findViewById(R.id.botonIgual)

        //TextView
        operacionActual = findViewById(R.id.operacionActual)
        historial = findViewById(R.id.anteriorResultado)
        memoria = findViewById(R.id.memoria)

    }
    private fun initList(){


        //Funciones con listener para los botones//

        //Numeros//

        ColocarNumero(boton0, "0")
        ColocarNumero(boton1, "1")
        ColocarNumero(boton2, "2")
        ColocarNumero(boton3, "3")
        ColocarNumero(boton4, "4")
        ColocarNumero(boton5, "5")
        ColocarNumero(boton6, "6")
        ColocarNumero(boton7, "7")
        ColocarNumero(boton8, "8")
        ColocarNumero(boton9, "9")

        //Para las operaciones//

        botonMas.setOnClickListener {
            if(operando == "0" || operando.isEmpty()){
                operando = "+"
                comprobarSegundo = true
                comprobarComa = false
                operacionActual.text = primerNumero +" "+ operando
            }else  if (segundoNumero == "0"){
                operando = "+"
                operacionActual.text = primerNumero +" "+ operando
            }

        }

        botonX.setOnClickListener {
            if(operando == "0"|| operando.isEmpty()){
                operando = "x"
                comprobarSegundo = true
                comprobarComa = false
                operacionActual.text = primerNumero +" "+ operando

            }else  if (segundoNumero == "0"){
                operando = "x"
                operacionActual.text = primerNumero +" "+ operando
            }

        }

        botonMenos.setOnClickListener {
            if(operando == "0" || operando.isEmpty()){
                operando = "-"
                comprobarSegundo = true
                comprobarComa = false
                operacionActual.text = primerNumero +" "+ operando
            }else  if (segundoNumero == "0"){
                operando = "-"
                operacionActual.text = primerNumero +" "+ operando

            }

        }

        botondivision.setOnClickListener {
            if(operando == "0" || operando.isEmpty() ){
                operando = "/"
                comprobarSegundo = true
                comprobarComa = false
                operacionActual.text = primerNumero +" "+ operando

            }else  if (segundoNumero == "0"){
                operando = "/"
                operacionActual.text = primerNumero +" "+ operando

            }

        }

        botonIgual.setOnClickListener {
            if(comprobarSegundo){
                operaciones = Operacion(primerNumero,segundoNumero,operando)
                historial.text =  operaciones.operacion()
                operacionActual.text = "0"
                comprobarSegundo = false
                comprobarComa = false
                operando = "0"
                primerNumero = "0"
                segundoNumero = "0"

            }
        }

        //Para la comas//

        botonPunto.setOnClickListener {
            if(!comprobarSegundo ){
                if(!comprobarComa){
                    primerNumero = primerNumero +"."
                    comprobarComa = true
                }
                operacionActual.text =  primerNumero

            }else{
                if (!comprobarComa){
                    segundoNumero = segundoNumero + "."
                    comprobarComa = true
                }

                operacionActual.text = primerNumero +" " +operando+ " "+ segundoNumero

            }
        }
        //Para la primera linea//

        buttonC.setOnClickListener()
        {
            if(comprobarSegundo){
                if(segundoNumero.isEmpty()){
                    operacionActual.text = primerNumero
                    operando = "0"
                    comprobarSegundo = false
                }else{
                    segundoNumero = segundoNumero.replaceFirst(".$".toRegex(),"")
                    operacionActual.text = primerNumero + " " + operando + " " + segundoNumero

                }
            }else
            {
                if( operando.isEmpty() || operando == "0"){
                    primerNumero = primerNumero.replaceFirst(".$".toRegex(),"")
                    operacionActual.text = primerNumero

                }else{
                    operacionActual.text = primerNumero

                }
            }
        }
        botonMMas.setOnClickListener(){
            numeroMemorizado = true
            memoria.text = "M"
            guardado = historial.text.toString()
        }


        botonM.setOnClickListener(){

            if(numeroMemorizado){
                primerNumero = historial.text.toString()
                segundoNumero = "0"
                operacionActual.text = guardado
                memoria.text = ""
                comprobarComa = false
                numeroMemorizado = false
                comprobarSegundo = false

            }else{

                operacionActual.text = "0"
                primerNumero = "0"
                segundoNumero = "0"
                comprobarSegundo = false
                comprobarComa = false
            }

        }
        botonAC.setOnClickListener(){
            operacionActual.text = "0"
            comprobarSegundo = false
            comprobarComa = false
            operando = "0"
            primerNumero = "0"
            segundoNumero = "0"
            historial.text = "0"
            memoria.text = ""
        }
    }

    private fun ColocarNumero(boton : Button,numero : String){
        boton.setOnClickListener {
            if(comprobarSegundo){
                if(segundoNumero != "0"){
                    segundoNumero = segundoNumero + numero
                }else{
                    segundoNumero = numero
                }
                operacionActual.text = primerNumero + " "+operando+" "+segundoNumero
            }else{
                if(primerNumero != "0"){
                    primerNumero = primerNumero + numero
                }else{
                    primerNumero = numero
                }
                operacionActual.text = primerNumero
            }

        }

    }
}