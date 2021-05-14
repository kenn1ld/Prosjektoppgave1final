package no.uia.ikt205.myhuskeliste

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import no.uia.ikt205.myhuskeliste.huskelister.MainhuskelisteCollectionAdapter
import no.uia.ikt205.myhuskeliste.huskelister.MainhuskelisteDepositoryManager
import no.uia.ikt205.myhuskeliste.huskelister.MainhuskelisteDetailsActivity
import no.uia.ikt205.myhuskeliste.huskelister.data.Mainhuskeliste
import no.uia.ikt205.myhuskeliste.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


const val EXTRA_HUSKELISTE_INFO: String = "no.uia.ikt205.myhuskeliste.huskeliste.info"
const val REQUEST_HUSKELISTE_DETAILS:Int = 564567

class HuskelisteHolder{

    companion object{
        var PickedHuskeliste: Mainhuskeliste? = null
    }


}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.mainhuskelisteListing.layoutManager = LinearLayoutManager(this)
        binding.mainhuskelisteListing.adapter = MainhuskelisteCollectionAdapter(emptyList<Mainhuskeliste>(), this::onHuskelisteClicked)


        MainhuskelisteDepositoryManager.instance.onMainhuskeliste = {
            (binding.mainhuskelisteListing.adapter as MainhuskelisteCollectionAdapter).updateCollection(it)
        }

        MainhuskelisteDepositoryManager.instance.load(getString(R.string.huskeliste_listing_url),this)


        binding.saveBt.setOnClickListener {
            val author = ""
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)
            val title = binding.title.text.toString()
            val published = "Added: $formatted"
           // binding.author.setText("")
            binding.title.setText("")
            //binding.published.setText("")

            addHuskeliste(title, published)

            val ipm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            ipm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)

        }
    }

    private fun addHuskeliste(title: String, published: String) {

        val mainhuskeliste = Mainhuskeliste(title)
        MainhuskelisteDepositoryManager.instance.addHuskeliste(mainhuskeliste)

    }



    private fun onHuskelisteClicked(mainhuskeliste: Mainhuskeliste): Unit {


        HuskelisteHolder.PickedHuskeliste = mainhuskeliste

        val intent = Intent(this, MainhuskelisteDetailsActivity::class.java)

        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_HUSKELISTE_DETAILS){

        }

    }


}