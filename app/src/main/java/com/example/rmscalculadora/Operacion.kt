package com.example.rmscalculadora

class Operacion(numero1 : String,numero2 : String,operacion : String) {

    private var primerNumero : Float = numero1.toFloat()
    private var segundoNumero : Float = numero2.toFloat()
    private var operacion : String = operacion
    private var resultado : Float = "0".toFloat()

    public fun operacion():String{

        when (operacion) {
            "x" -> {
                resultado = primerNumero *  segundoNumero
                return resultado.toString()

            }
            "/"-> {
                if(segundoNumero == "0".toFloat())
                {
                    return "Syntax Error"
                }
                resultado = primerNumero /  segundoNumero
                return resultado.toString()
                }
            "-" -> {
                resultado = primerNumero -  segundoNumero
                return resultado.toString()
            }

            "+" -> {
                resultado = primerNumero +  segundoNumero
                return resultado.toString()
            }
            else -> {
                return "Ha habido un problema"
            }
        }

    }

}