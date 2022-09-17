package com.example.wordle

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    val wordToGuess = getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateText()
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */

    private fun checkGuess(guess: String) : String {
        var result = ""

        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    private fun updateText() {

        val button = findViewById<Button>(R.id.guessButton)
        val guess1 = findViewById<TextView>(R.id.guess1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        val word1 = findViewById<TextView>(R.id.word1)
        val word2 = findViewById<TextView>(R.id.word2)
        val word3 = findViewById<TextView>(R.id.word3)
        val result = findViewById<TextView>(R.id.textView7)
        val editText = findViewById<EditText>(R.id.editText)
        var guessCount = 0

        button.setOnClickListener{

            result.visibility = View.INVISIBLE
            result.text = wordToGuess

            if(guessCount == 0) {
                guess1.text = editText.text.toString().uppercase()
                closeKeyboard(editText)
                editText.setText("")
                word1.text = checkGuess(guess1.text.toString().uppercase())
                if(word1.text == "OOOO") {
                    Toast.makeText(applicationContext, "Great job!", Toast.LENGTH_SHORT).show()
                }
                guessCount += 1
            }
            else if(guessCount == 1) {
                guess2.text = editText.text.toString().uppercase()
                closeKeyboard(editText)
                editText.setText("")
                word2.text = checkGuess(guess2.text.toString().uppercase())
                if(word2.text == "OOOO") {
                    Toast.makeText(applicationContext, "Great job!", Toast.LENGTH_SHORT).show()
                }
                guessCount += 1
            }
            else if(guessCount == 2) {
                guess3.text = editText.text.toString().uppercase()
                closeKeyboard(editText)
                editText.setText("")
                word3.text = checkGuess(guess3.text.toString().uppercase())
                if (word3.text == "OOOO") {
                    Toast.makeText(applicationContext, "Great job!", Toast.LENGTH_SHORT).show()
                }
                else {
                    button.isEnabled = false
                    result.visibility = View.VISIBLE
                }
                guessCount += 1
            }
        }
    }

    private fun closeKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}