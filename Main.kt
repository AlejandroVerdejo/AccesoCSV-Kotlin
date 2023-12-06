import java.io.File

fun main() 
{
    //Creamos la variable con el archivo que queremos leer
    val archivo = File("notas.csv")
    //Creamos una lista indefinida para introducir los alumnos y sus notas medias
    var medias = hashMapOf<String, Double>()
    //Creamos una variable que almacene las lineas del archivo
    var lectura = mutableListOf<String>()
    if (archivo.exists()) 
    {
        //Creamos la variable bufferedreader para leer el archivo
        val leer = archivo.bufferedReader()
        //Almacenamos todas las lineas y las leemos una a una con un foreach
        //Añadiremos cada linea a la lista creada anteriormente
        leer.useLines { linea -> linea.forEach {lectura.add(it) }}
        //Cerramos el archivo
        leer.close()
    }
    //Ahora trabajaremos con cada linea utilizando un foreach en la lista
    lectura.forEach {i -> 
        //Separamos por el divisor del archivo para sacar el nombre del alumno
        var nombre = i.split(":").first()
        //Separamos por el divisor del archivo para sacar las notas del alumno
        var notas = i.split(":").drop(1)
        //Pasamos las notas a Double
        var notasDouble = notas.map { it.toDoubleOrNull() ?: 0.0 }
        //Utilizamos el metodo para sacar la media enviando la suma de las notas y la cantidad de notas
        //El metodo reduce se encarga de sumar todos los elementos de la lista de double
        var media = media(notasDouble.reduce { acumulado, elemento -> acumulado + elemento },notasDouble.size)
        //Añadimos el nombre y la nota media a la lista
        medias.put(nombre,media)   
    }
    //Muestra todo el contenido de la lista de medias y alumnos
    medias.forEach {(key, value) -> println("Alumno: $key  Media: $value")}

}
//Metodo para calcular la nota media
//Recibe el Double con la suma de las notas y el Int con la cantidad de notas
fun media(notas: Double, nNotas: Int): Double
{
    //Divide la suma de notas entre la cantidad de notas
    var media = notas/nNotas
    //Devuelve la nota media
    return media
}