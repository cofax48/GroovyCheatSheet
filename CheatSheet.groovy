///From http://grails.asia/groovy-file-examples and associated opther tutorials

//groovy for loop with times-basically it does the thing however many times you tell it
def sumNum = 1
5.times {
  println it
  sumNum += it
  println "Hello World ${sumNum}"
}

//for loop with list
def list = ["Austria", "Belgium", "Canada"]
for (item in list) {
  println item
}

//iterate over range of numbers
for (number in 11..15) {
  println number
}

//iterating through a map
def map = [a1:'first val', a2:'second val']
for (item in map) {
  println item.key
  println item.value
}

//iterating through a range using each
(1..3).each {
  println it
}

//iterating through list with index
def lister = ["America", "Brazil", "Congo"]
lister.eachWithIndex { val, idx ->
  println "${idx}. ${val}"
}

/////////////////////////////////////////////
//STRINGS
////////////////////////////////////////////

def maName = "Joshua Charles Woods"
println maName.length() //string length
println maName.substring(1) //everything after that index position -- could be good for taking slice
println maName.indexOf('o') //gets index for first match
println maName.replace("Woods", "Schenkein") //replaces item with new var
println maName.toLowerCase()

//concatenation is the same:
println maName + ' is da greatest'

//Strings in double quotes are called GStrings ----single quotes will not have the same effect
def firstName = 'Joooosh'
def lastName = 'Wooooooods'
println "${firstName} +  is da greatest + ${lastName}"

println maName - 'W' //produces Joshua Charles oods as the W is dropped
println maName * 2 //makes the variable repeat twice

//Similar pythonic special characters
println 'Jooosh\tWoods' //tabs
println 'Joosh\nWoods' // newline

def str1 = """This is a
multileine
statement with
three quotes"""
println str1

//padding
def newNamez = maName.padLeft(50)
println newNamez
println newNamez.length()

//String to list or Tokenize
def nameList = maName.tokenize()
println nameList

//Closures are short blocks of anonomyous code. They span normally just a few lines of code
//. A method can even take the block of code as a parameter. They are anonymous in nature
//Simple closure
class Example {
  static void main(String[] args) {
    def clos = {param->println "Hello ${param}"}; //This itself is the closure
    println clos.call("World");
  }
}

println maName.find{ it > 'C'} //output o`
println maName.findAll{ it > 'J' } //[o, s, h, u, a, h, a, r, l, e, s, W, o, o, d, s]
println maName.findAll{ it >= 's'} //[s, u, s, s]
println maName.every{ it > 'J'}

//collect will construct a new list by manipulating each caharcter
println maName.collect{ it } //[J, o, s, h, u, a,  , C, h, a, r, l, e, s,  , W, o, o, d, s]
println maName.collect{ it + '.' } //[J., o., s., h., u., a.,  ., C., h., a., r., l., e., s.,  ., W., o., o., d., s.]
println maName.collect{ 'yao' + it } //[yaoJ, yaoo, yaos, yaoh, yaou, yaoa, yao , yaoC, yaoh, yaoa, yaor, yaol, yaoe, yaos, yao , yaoW, yaoo, yaoo, yaod, yaos]
println maName.collect{ it * 3}//[JJJ, ooo, sss, hhh, uuu, aaa,    , CCC, hhh, aaa, rrr, lll, eee, sss,    , WWW, ooo, ooo, ddd, sss]


////////////////////////////////////////////////
//////// MAPS
////////////////////////////////////////////////

//Maps are colelctions of key value pairs-aka dictionaries/hash tables/objects
//Commonly used for implementing lookups or cache
def salaryMap = [:]
salaryMap["Joosh"] = "25"
salaryMap["Mike"] = "10"
println salaryMap

println "Salary of Josh is: ${salaryMap['Joosh']}"
//keys can be written with quotes or without
def personalDetails = [firstName:'balthzar', lastName:'Monk', age:28]
println "First Name ${personalDetails['firstName']}"
println "First Name ${personalDetails.firstName}"
println "Last Name ${personalDetails['lastName']}"
println "Last Name ${personalDetails.lastName}"
println "Age ${personalDetails['age']}"
println "Age ${personalDetails.age}"

//Maps can also be populated with instances of other classes
def samplemap = [99:new Long(99), (BigDecimal.valueOf(5)):new Double(5)]
println samplemap

//different ways of adding values to a map
def samp2map = [:] //intializes empty map
samp2map.put('thickness', 10) //use put method because a Groovy map is also an instance of java.util.Map
samp2map['color'] = 'Blue' //Enclose keys inside squareb brackets
samp2map.weight = 500 //use bean notation
samp2map.'shape' = 'Circle' //also valid bean notation
samp2map << [price:150] //add values from another map
println samp2map

//Replacing values on a map
samp2map.put('thickness', 5)
samp2map['color'] ='Red'
samp2map.weight = 200
samp2map.'shape' = 'Rectangle'
samp2map << [price:99]
println samp2map

//Looking up values from a map
println "The color is: ${samp2map['color']}"
println "The weight is ${samp2map.get('weight')}"
println "The shape is: ${samp2map.shape}"
println "The thickness is ${samp2map.'thickness'}"

//removing k,v from a map
samp2map.remove('color') //removes by key
samp2map = samp2map - ['shape':'Rectangle'] //removes if k/v is as expected but makes new list-may be mroe memory intensive
samp2map -= [weight:200] //removes k/v without making enw image
println samp2map

//Assertions
//We can make a lookup a return value if the key is not found in the map
assert 'Red' == [color:'Red', shape:'blue'].get('color', 'Blue') // this doesn't fail

def x = 1
assert x == 1 // if true nothing is displayed and continues normally if false -very detailed printout
//assert x == 2  //

def wordList = ['Apple', 'Banana', 'Cat']
def wordCountMap = wordList.collectEntries{ [(it):it.length()]} //takes an existing array and creates a new map populated with the length of the items in the array
println wordCountMap

//Count
//We can count how many items in the map satisfies a condition
def longWords = wordCountMap.count { key, value->
  value >4
}
println "${longWords}" //equals 2 because only 2 words have lengths longer than 4

//Union
//We can add maps to combine their contents
def map1 = [p1:100, p2:300] //p2 being duplicated in both maps is only added once
def map2 = [p2:200, p4:400] //p2 also only takes the value of the second adding
def map3 = map1 + map2
println "${map3}"

//Intersections
//We can get the intersection of two maps
def map4 = [p1:100, p2:200, p3:300, p8:800, p9:900]
def map5 = [p4:400, p3:300, p6:600]
def mapinter = map4.intersect(map5)
println "${mapinter}" //prints [p3:300] where both points of intersection occur

println "${map4.keySet()}" //prints all the keys and not the values
//conversely to get all values
println "${map4.values()}"

def map4Keys = map4.keySet()
def map4Values = map4.values()

//intialize empty map
def map4Reconstructed = [:]
//loop through map4Keys array and create a value which will become the new the
//key in the new array and an index position so that when we want to capture the
//value of the value in the second array we can pull it up by the index position-we also do this by using a closure
map4Keys.eachWithIndex{ val, idx ->
  map4Reconstructed[val] = map4Values[idx]
}
println map4Reconstructed

//Min and Max
//We can get the lowest entry in the map given an expression inside a closure
println "${map4Reconstructed.min {it.key}}"
println "${map4Reconstructed.max {it.value}}"

//////////////////////////
//// Arrays
//////////////////////

//standard java ways
def testArray = new String[3]
testArray[0] = "A"
testArray[1] = "B"
testArray[2] = "C"

//By declaring type super standard java
String[] testArray1 = ["A", "B", "C"]

//using as syntax for coercion
def testArray2 = ["A", "B", "C"] as String[]

//Alternative syntax for type
def testArray3 = (String[]) ["A", "B", "C"]

//sorting a list
Integer[] testArray4 = [200, 300, 100];
println testArray4
testArray4.sort()
println testArray4
//makes the array of nintegers a string
testArray5 = testArray4 as String[]
//creates a new array of strings from the reversed string array
String[] reverse5 = testArray5.reverse()
println reverse5

///////////////////////////////////
////File Manipulation
////////////////////////////////

//gets the file objects
File maFile = new File('p067_triangle.txt')

class FileExample {
  static void main(String[] args) {
    File maFile = new File('p067_triangle.txt')
    println "The file ${maFile.absolutePath} has ${maFile.length()} bytes"
    println "Below is the contents of ${maFile}"
    println maFile.text /////////prints out the whole file as a string

  }
}
//how you call a method in a class (all classes will have at least one method)-or call
//new FileExample().main()

//to read the contents of a file line by line
class FileLineByLine {
  static void main(String[] args) {
    File maFile = new File('p067_triangle.txt')
    def lineNo = 1
    def line
    maFile.withReader { reader ->
      while ((line = reader.readLine())!=null) {
        println "${lineNo}. ${line}"
        lineNo++
      }
    }
  }
}
//new FileLineByLine().main() //calls the function and whatever code runs inside of it-since i have a println it will println

//Read contents of file to a list
class ReadFileToList {
  static void main(String[] args) {
    File maFile = new File('p067_triangle.txt')
    def lines = maFile.readLines()
    println "${maFile} has ${lines.size()} lines of text"
    println "Here is the first line: ${lines[0]}"
    println "Here is the last line: ${lines[lines.size()-1]}"
  }
}
//new ReadFileToList().main()

//Write to a file
class ReadAndWriteToFile {
  static void main(String[] args) {
    File maFile = new File('p067_triangle.txt')
    def oldFile = maFile.readLines()
    def lastLine = oldFile[oldFile.size()-1]
    File newFile = new File("testFile.txt")
    newFile.write "This is a new file and these are lines from the old file ${lastLine}"
    newFile << "This is the second line"
    newFile << "This is the third line and some random stuff from the old file ${oldFile[oldFile.size()-10]}"
    println newFile.text
  }
}
//new ReadAndWriteToFile().main()

//Test if file or directory
class FileOrDirectory {
  static void main(String[] args) {
    def maFile = new File('p067_triangle.txt')
    println "File? ${maFile.isFile()}"//tests if file returns boolean
    println "Directory? ${maFile.isDirectory()}"//tests if directory returns boolean
    println "Hidden? ${maFile.isHidden()}" //tests if hidden boolean
  }
}
//new FileOrDirectory().main()

////////////////////////
//Type Casting
////////////////////////
def stringNumber = "100"
def nowInteger = stringNumber.toInteger();
println nowInteger
def intValue = stringNumber.isInteger() ? stringNumber.toInteger() : null //will be null if stringNumber cant be cast to int--uses ternary operator
println intValue
//as int
def asInt = stringNumber as int;
println asInt;
//parse int
def intValueParse = Integer.parseInt(stringNumber)
println intValueParse
//Integer.valueOf
def intvalueOf = Integer.valueOf(stringNumber)
println intvalueOf;
//new integer object
def intValueNew = new Integer(stringNumber).intValue()
println intValueNew
//As decimal
//If java class isnt found or recognized consider importing it
import java.text.DecimalFormat;
DecimalFormat df = new DecimalFormat("0.##")
def intvalDec = df.parse(stringNumber).intValue();
println(intvalDec)
