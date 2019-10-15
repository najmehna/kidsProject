package com.najmehnasiriyani.kidsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_take_survey.*
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class TakeSurveyActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()

    val countryAndFlag = arrayListOf(
        "Select Country",
        "🇦🇪 United Arab Emirates", "🇦🇫 Afghanistan", "🇦🇬 Antigua & Barbuda", "🇦🇮 Anguilla",
    "🇦🇱 Albania", "🇦🇲 Armenia", "🇦🇴 Angola", "🇦🇶 Antarctica", "🇦🇷 Argentina", "🇦🇸 American Samoa",
    "🇦🇹 Austria", "🇦🇺 Australia", "🇦🇼 Aruba", "🇦🇽 Åland Islands", "🇦🇿 Azerbaijan", "🇧🇦 Bosnia & Herzegovina",
    "🇧🇧 Barbados", "🇧🇩 Bangladesh", "🇧🇪 Belgium", "🇧🇫 Burkina Faso", "🇧🇬 Bulgaria", "🇧🇭 Bahrain", "🇧🇮 Burundi",
    "🇧🇯 Benin", "🇧🇱 St. Barthélemy", "🇧🇲 Bermuda", "🇧🇳 Brunei", "🇧🇴 Bolivia", "🇧🇶 Caribbean Netherlands", "🇧🇷 Brazil",
    "🇧🇸 Bahamas", "🇧🇹 Bhutan", "🇧🇻 Bouvet Island", "🇧🇼 Botswana", "🇧🇾 Belarus",
        "🇧🇿 Belize", "🇨🇦 Canada", "🇨🇨 Cocos (Keeling) Islands",
    "🇨🇩 Congo - Kinshasa", "🇨🇫 Central African Republic", "🇨🇬 Congo - Brazzaville", "🇨🇭 Switzerland", "🇨🇮 Côte d’Ivoire",
    "🇨🇰 Cook Islands", "🇨🇱 Chile", "🇨🇲 Cameroon", "🇨🇳 China", "🇨🇴 Colombia", "🇨🇵 Clipperton Island", "🇨🇷 Costa Rica",
    "🇨🇺 Cuba", "🇨🇻 Cape Verde", "🇨🇼 Curaçao", "🇨🇽 Christmas Island", "🇨🇾 Cyprus",
    "🇨🇿 Czechia", "🇩🇪 Germany", "🇩🇬 Diego Garcia", "🇩🇯 Djibouti", "🇩🇰 Denmark", "🇩🇲 Dominica",
    "🇩🇴 Dominican Republic", "🇩🇿 Algeria", "🇪🇦 Ceuta & Melilla", "🇪🇨 Ecuador", "🇪🇪 Estonia", "🇪🇬 Egypt",
    "🇪🇭 Western Sahara", "🇪🇷 Eritrea", "🇪🇸 Spain", "🇪🇹 Ethiopia", "🇪🇺 European Union", "🇫🇮 Finland", "🇫🇯 Fiji",
    "🇫🇰 Falkland Islands", "🇫🇲 Micronesia", "🇫🇴 Faroe Islands", "🇫🇷 France", "🇬🇦 Gabon", "🇬🇧 United Kingdom",
    "🇬🇩 Grenada", "🇬🇪 Georgia", "🇬🇫 French Guiana", "🇬🇬 Guernsey", "🇬🇭 Ghana", "🇬🇮 Gibraltar",
    "🇬🇱 Greenland", "🇬🇲 Gambia", "🇬🇳 Guinea", "🇬🇵 Guadeloupe", "🇬🇶 Equatorial Guinea", "🇬🇷 Greece",
    "🇬🇸 South Georgia & South Sandwich Islands", "🇬🇹 Guatemala", "🇬🇺 Guam", "🇬🇼 Guinea-Bissau", "🇬🇾 Guyanav", "🇭🇰 Hong Kong SAR China",
    "🇭🇲 Heard & McDonald Islands", "🇭🇳 Honduras", "🇭🇷 Croatia", "🇭🇹 Haiti", "🇭🇺 Hungary", "🇮🇨 Canary Islands", "🇮🇩 Indonesia",
    "🇮🇪 Ireland", "🇮🇱 Israel", "🇮🇲 Isle of Man", "🇮🇳 India", "🇮🇴 British Indian Ocean Territory",
    "🇮🇶 Iraq", "🇮🇷 Iran", "🇮🇸 Iceland", "🇮🇹 Italy", "🇯🇪 Jersey", "🇯🇲 Jamaica", "🇯🇴 Jordan", "🇯🇵 Japan", "🇰🇪 Kenya",
    "🇰🇬 Kyrgyzstan", "🇰🇭 Cambodia", "🇰🇮 Kiribati", "🇰🇲 Comoros", "🇰🇳 St. Kitts & Nevis", "🇰🇵 North Korea", "🇰🇷 South Korea", "🇰🇼 Kuwait",
    "🇰🇾 Cayman Islands", "🇰🇿 Kazakhstan", "🇱🇦 Laos", "🇱🇧 Lebanon", "🇱🇨 St. Lucia", "🇱🇮 Liechtenstein", "🇱🇰 Sri Lanka",
    "🇱🇷 Liberia", "🇱🇸 Lesotho", "🇱🇹 Lithuania", "🇱🇺 Luxembourg", "🇱🇻 Latvia", "🇱🇾 Libya", "🇲🇦 Morocco", "🇲🇨 Monaco", "🇲🇩 Moldova",
    "🇲🇪 Montenegro", "🇲🇫 St. Martin", "🇲🇬 Madagascar", "🇲🇭 Marshall Islands", "🇲🇰 North Macedonia", "🇲🇱 Mali", "🇲🇲 Myanmar (Burma)", "🇲🇳 Mongolia",
    "🇲🇴 Macao Sar China", "🇲🇵 Northern Mariana Islands", "🇲🇶 Martinique", "🇲🇷 Mauritania", "🇲🇸 Montserrat", "🇲🇹 Malta", "🇲🇺 Mauritius",
    "🇲🇻 Maldives", "🇲🇼 Malawi", "🇲🇽 Mexico", "🇲🇾 Malaysia", "🇲🇿 Mozambique", "🇳🇦 Namibia", "🇳🇨 New Caledonia",
    "🇳🇪 Niger", "🇳🇫 Norfolk Island", "🇳🇬 Nigeria", "🇳🇮 Nicaragua", "🇳🇱 Netherlands", "🇳🇴 Norway", "🇳🇵 Nepal",
    "🇳🇷 Nauru", "🇳🇺 Niue", "🇳🇿 New Zealand", "🇴🇲 Oman", "🇵🇦 Panama", "🇵🇪 Peru", "🇵🇫 French Polynesia", "🇵🇬 Papua New Guinea",
    "🇵🇭 Philippines", "🇵🇰 Pakistan", "🇵🇱 Poland", "🇵🇲 St. Pierre & Miquelon", "🇵🇳 Pitcairn Islands", "🇵🇷 Puerto Rico", "🇵🇸 Palestinian Territories",
    "🇵🇹 Portugal", "🇵🇼 Palau", "🇵🇾 Paraguay", "🇶🇦 Qatar", "🇷🇪 Réunion", "🇷🇴 Romania", "🇷🇸 Serbia",
    "🇷🇺 Russia", "🇷🇼 Rwanda", "🇸🇦 Saudi Arabia", "🇸🇧 Solomon Islands", "🇸🇨 Seychelles", "🇸🇩 Sudan", "🇸🇪 Sweden", "🇸🇬 Singapore",
    "🇸🇭 St. Helena", "🇸🇮 Slovenia", "🇸🇯 Svalbard & Jan Mayen", "🇸🇰 Slovakia", "🇸🇱 Sierra Leone", "🇸🇲 San Marino", "🇸🇳 Senegal", "🇸🇴 Somalia",
    "🇸🇷 Suriname", "🇸🇸 South Sudan", "🇸🇹 São Tomé & Príncipe", "🇸🇻 El Salvador", "🇸🇽 Sint Maarten", "🇸🇾 Syria", "🇸🇿 Eswatini", "🇹🇦 Tristan Da Cunha",
    "🇹🇨 Turks & Caicos Islands", "🇹🇩 Chad", "🇹🇫 French Southern Territories", "🇹🇬 Togo", "🇹🇭 Thailand", "🇹🇯 Tajikistan", "🇹🇰 Tokelau",
    "🇹🇱 Timor-Leste", "🇹🇲 Turkmenistan", "🇹🇳 Tunisia", "🇹🇴 Tonga", "🇹🇷 Turkey", "🇹🇹 Trinidad & Tobago", "🇹🇻 Tuvalu",
    "🇹🇼 Taiwan", "🇹🇿 Tanzania", "🇺🇦 Ukraine", "🇺🇬 Uganda", "🇺🇲 U.S. Outlying Islands", "🇺🇳 United Nations",
    "🇺🇸 United States", "🇺🇾 Uruguay", "🇺🇿 Uzbekistan", "🇻🇦 Vatican City", "🇻🇨 St. Vincent & Grenadines", "🇻🇪 Venezuela",
    "🇻🇬 British Virgin Islands", "🇻🇮 U.S. Virgin Islands", "🇻🇳 Vietnam", "🇻🇺 Vanuatu", "🇼🇫 Wallis & Futuna", "🇼🇸 Samoa", "🇽🇰 Kosovo", "🇾🇪 Yemen",
    "🇾🇹 Mayotte", "🇿🇦 South Africa", "🇿🇲 Zambia", "🇿🇼 Zimbabwe", "🏴󠁧 England", "🏴󠁧 Scotland", "🏴󠁧󠁢󠁷󠁬󠁳󠁿  Wales"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_survey)
    submitButton.setOnClickListener {
        if(fieldsAreOk()) {
            createChild()
            emptyFields()
        }
    }
        cancelButton.setOnClickListener {
            this.finish()
        }
       // val myCountryAdapter = customSpinnerAdapter(countryAndFlag, this)
        val myCountryAdapter = customSpinnerAdapter(countryAndFlag, this)
        spinner3.adapter = myCountryAdapter
//        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//
//            }
//        }
        }
    fun emptyFields(){
        DOBText.setText("")
        spinner3.setSelection(0)
        spinner.setSelection(0)
        spinner2.setSelection(0)
        childNameText.setText("")
        childNameText.requestFocus()
    }
    fun createChild(){
        val child = Child(childNameText.text.toString(),spinner3.selectedItem.toString(),spinner.selectedItem.toString(), DOBText.text.toString(), spinner2.selectedItem.toString())
        db.collection("Kids").add(child)
    }
    fun fieldsAreOk(): Boolean{
        if (childNameText.getText().toString() == "" || spinner3.selectedItemPosition == 0 || spinner.selectedItemPosition == 0 || spinner2.selectedItemPosition == 0 || DOBText.getText().toString() == "") {
            Toast.makeText(this, "Please fill all fields...", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}
