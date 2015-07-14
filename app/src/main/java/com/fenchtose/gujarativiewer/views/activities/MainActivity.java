package com.fenchtose.gujarativiewer.views.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.fenchtose.gujarativiewer.R;
import com.fenchtose.gujarativiewer.controllers.services.ClipboardService;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Lohit-Gujarati.ttf");

        TextView textView = (TextView)findViewById(R.id.textview_info);
        textView.setTypeface(typeface);

        TextView headerTextView = (TextView)findViewById(R.id.textview_header);
        headerTextView.setTypeface(typeface);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        TextView textViewInfo2 = (TextView)findViewById(R.id.textview_info_2);
        textViewInfo2.setTypeface(typeface);

        Intent serviceIntent = new Intent(this, ClipboardService.class);
        startService(serviceIntent);
        try {
            doXML(content);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<NewsHeadlines>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[US: ખાનગી પ્લેન ક્રેશ, સાત વર્ષની છોકરીનો બચી ગયો જીવ]]></title>\n" +
            "\t<shortDescription><![CDATA[Girl survives US plane crash that kills four others]]></shortDescription>\n" +
            "\t<description><![CDATA[(પ્રતીકાત્મક તસવીર)\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "ઇન્ટરનેશનલ ડેસ્કઃ અમેરિકન સમયાનુસાર શુક્રવાર રાત્રે કેન્ટૂકી નજીકના જંગલ વિસ્તારમાં એક પ્રાઇવેટ વિમાન ક્રેશ થયું છે. અમેરિકન પોલીસે સ્થાનિક મીડિયાને જણાવ્યાનુસાર, મિનિ વિમાનનાં સવાર ચાર લોકોના મૃત્યુ થયા હતા, પરંતુ અકસ્માતમાં સાત વર્ષની છોકરીનો ચમત્કારી બચાવ થયો છે. ક્રેશ થયેલા વિમાનના કાટમાળમાંથી સાત વર્ષની છોકરી જીવિત બહાર નીકળી હતી.&nbsp;\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "પોલીસે ફેસબુક પોસ્ટ પર જણાવ્યું હતું કે, સ્થાનિક રહેવાસીએ ફોન કરીને ઘટના અંગે જાણકારી આપી હતી. ફોન કરનારે પોલીસને જણાવ્યું હતું કે, સાત વર્ષની છોકરી કાટમાળમાંથી જીવતી બહાર આવી છે.&nbsp;\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "છોકરીને સામાન્ય ઇજાઓ પહોંચી છે અને તેની નજીકની હોસ્પિટલમાં સારવાર ચાલી રહી છે.]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-04 12:34:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[US પ્રેસિડન્ટનો કાફલોઃ ચકલુય ના ફરકે, મોદી, જીનપિંગ પાણી ભરે!]]></title>\n" +
            "\t<shortDescription><![CDATA[Obama\\'s India visit: convoy of America\\'s president]]></shortDescription>\n" +
            "\t<description><![CDATA[\n" +
            "(અમેરિકન પ્રમુખના કાફલાની ફાઇલ તસવીર)\n" +
            "\n" +
            "ઈન્ટરનેશનલ ડેસ્કઃ અમેરિકાના રાષ્ટ્રપતિ બરાક ઓબામા આજથી ભારતની મુલાકાતે આવ્યા હતા ત્યારે દિલ્હીવાસીઓમાં તેના કાફલાને લઈને ભારે ઉત્સુક્તા જોવા મળી રહી હતી. અલબત્ત, દિલ્હીવાસીઓ માટે કોઈ રાષ્ટ્રના વડાનો કાફલો જોવો એ કોઈ નવી વાત નથી. વડાપ્રધાનના કાફલાથી દિલ્હીવાસીઓ વાકેફ જ છે. વડાપ્રધાન નરેન્દ્ર મોદીના કાફલાને પણ દિલ્હીવાસીઓ રોજ નિહાળે છે. પણ, જો, મોદી અને અન્ય કોઈ રાષ્ટ્રવડાના કોન્વોયથી કોઈ આશ્ચર્યચકિત થતું હોય તો અહીં જણાવી દઈએ કે અમેરિકાના રાષ્ટ્રપતિ જેવો ભવ્ય કાફલો કોઈનો હોતો નથી.\n" +
            "\n" +
            "દુનિયાના સૌથી શક્તિશાળી રાષ્ટ્રના પ્રમુખ બરાક ઓબામાનો કાફલો જ્યારે નીકળે ત્યારે આપણા નેતાઓ કે દુનિયા આખીના નેતાઓને દબદબો બે ઘડી કમ પડી જાય. વળી, કાફલાની વ્યવસ્થાની પણ વિશેષતા હોય છે. ઓબામાની લિમોઝીન કે કેડિલેકની આગળ લગભગ 10 જેટલી કાર દોડતી હોય છે. એ સાથે અન્ય સુરક્ષા અને સુવિધા પુરી પાડતી કારો પણ દોડતી હોય છે. જેમાં SWATની ટીમ, કોમ્યુનિકેશન, સિક્રેટ સર્વિસ, રડાર જામિંગ વેહિકલ, એમ્બ્યુલન્સ પણ દોડતી હોય છે, જે ગમે તેવી પરિસ્થિતીનો સામનો કરવો તૈયાર હોય છે.\n" +
            "&nbsp;\n" +
            "\n" +
            "આગળ જુઓ, ઓબામા, મોદી અને જિનપિંગના કાફલાની કેટલીક વિશિષ્ટ બાબતો અને તસવીરો...\n" +
            "]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-03 09:56:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[ધાર્મિક કાર્યક્રમમાં ઓબામા મળશે દલાઇ લામાને, ચીન થઇ શકે છે નારાજ]]></title>\n" +
            "\t<shortDescription><![CDATA[US President Barack Obama will appear in public at an event attended by the Dalai Lama next week in Washington]]></shortDescription>\n" +
            "\t<description><![CDATA[(ધર્મગુરુ દલાઇ લામા અને અમેરિકન રાષ્ટ્રપતિ બરાક ઓબામાની ફાઇલ તસવીર)\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "વોશિંગ્ટનઃ અમેરિકાના રાષ્ટ્રપતિ બરાક ઓબામા અને તિબ્બતના ધર્મગુરુ દલાઇ લામા વોશિંગ્ટનમાં આવતા સપ્તાહમાં થનારા એક જાહેર કાર્યક્રમમાં એક સાથે જોવા મળશે. ઓબામા અને દલાઇ લામાના એક મંચ સાથે આવતા ચીન નારાજ થાય તેવી સંભાવના છે.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "મળતી વિગતો અનુસાર પાંચમી ફેબ્યુઆરીના રોજ થનારા &lsquo;નેશનલ પ્રેયર બ્રેકફાસ્ટ&rsquo;માં ઓબામા ઉપસ્થિત લોકોને ધાર્મિક સ્વતંત્રતાના મહત્વ પર સંબોધિત કરશે. બંન્ને નેતાઓ વચ્ચે એક વર્ષથી પણ ઓછા સમયમાં આ બીજી મુલાકાત હશે. આ કાર્યક્રમમાં તિબ્બતીઓના ધાર્મિક નેતા દલાઇ લામા પણ હાજરી આપશે. ઉલ્લેખનીય છે કે દલાઇ લામા 1959માં તિબ્બતમાં ચીન સરકારના વિરુદ્ધ અસફળ આંદોલન બાદ દેશ છોડી &nbsp;ભારત આવ્યા હતા.ચીનનો આરોપ છે કે દલાઇ લામા અલગતાવાદી છે અને તેઓ ચીન અને તિબ્બતને અલગ કરવાના પ્રયાસમાં લાગેલા છે.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "આ કાર્યક્રમ દરમિયાન ઓબામા દલાઇ લામાને મળશે કે નહી તે પર કાંઇ કહેવાનો વ્હાઇટ હાઉસે ઇનકાર કર્યો હતો. નેશનલ સિક્યોરીટી કાઉન્સિલના પ્રવક્તા બર્નાડેટ મીહને કહ્યું કે રાષ્ટ્રપતિ કાર્યક્રમમાં ઘણા ધાર્મિક નેતાઓને મળશે પરંતુ દલાઇ લામા સાથે વિશેષ બેઠકની કોઇ યોજના નથી.]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-01 17:47:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[\\'અમેરિકાનું ભવિષ્ય ભારત સાથે\\', પ્રેસિડન્ટ ઓબામાનો CNNને ઈન્ટરવ્યૂ]]></title>\n" +
            "\t<shortDescription><![CDATA[Obama\\'s interview with Fareed Zakaria in GPS for CNN]]></shortDescription>\n" +
            "\t<description><![CDATA[(ઓબામાએ CNNને આપેલા ઈન્ટરવ્યૂનો સ્ક્રિન શોટ)\n" +
            "&nbsp;\n" +
            "\n" +
            "વોશિંગ્ટનઃ અમેરિકાના રાષ્ટ્રપતિ બરાક ઓબામાએ ભારત મુલાકાત બાદ અમેરિકામાં CNNના ફરીદ ઝકરીયાને આપેલા ઈન્ટરવ્યૂમાં ઈન્ડો-યુએસ રિલેશનશિપ, બન્ને રાષ્ટ્રોના સંબંધોને લઈને ચીનની ચિંતા મુદ્દે પોતાનો મત વ્યક્ત કર્યો હતો. તેમણે અમેરિકાની વિદેશ નીતિ પર ચર્ચા કરતા આતંકવાદ, રેડિકલ ઈસ્લામ, ડ્રોન વિમાનોના ઉપયોગ, સાઉદી અરેબીયાના કિંગ અબ્દુલ્લાના નિધન બાદ મધ્યપૂર્વના રાષ્ટ્રો સાથેની પોલીસી, યુએસ-ઈઝરાયેલની ભાગીદારી, યુએસ-રશિયા વચ્ચેના &nbsp;સંબંધો જેવા મુદ્દાઓ પર પોતાનો પક્ષ રજુ કર્યો હતો. ઓબામાએ ભારત-અમેરિકાના સંબંધો ચીન માટે ખતરારૂપ ના હોવાનું જણાવ્યું હતું. સાથે જ, ભારત સાથેના સંબંધો અમેરિકાના ભવિષ્ય અને સુરક્ષામાં કેવાક અસરકારક નિવડશે તેનો ઉલ્લેખ કર્યો હતો.&nbsp;\n" +
            "\n" +
            "\n" +
            "ચીનની ચિંતા આશ્ચર્યજનક&nbsp;\n" +
            "\n" +
            "ઓબામાની ભારત મુલાકાતને લઈને ચીને જાહેર કરેલી નારાજગી અને ચિંતાને આશ્ચર્યજનક ગણાવતા ઓબામાએ જણાવ્યું કે કે નવી દિલ્હી અને વોશિંગ્ટન વચ્ચેની સારા સંબંધોને લીધે ચીનને ખતરો અનુભવવાની કોઈ જરૂર નથી. ઓબામાએ કહ્યું કે &#39;ચીન સરકારે જ્યારે સંબંધીત નિવેદન જાહેર કર્યું ત્યારે મને આશ્ચર્ય થયું હતું. ભારત સાથેના આપણા સારા સંબંધોને લીધે ચીને ગભરાવાની જરૂર નથી. &#39; ઓબામાની ત્રણ દિવસીય મુલાકાત દરમિયાન બે વખત ચીન દ્વારા ભારત અને અમેરિકાની કરાયેલી ટિકાના પૂછાયેલા પ્રશ્નમાં ઓબામાએ સંબંધીત વાત કરી હતી.&nbsp;\n" +
            "&nbsp;\n" +
            "\n" +
            "મોદી અને ઓબામાનું વિઝન&nbsp;\n" +
            "&nbsp;\n" +
            "\n" +
            "ઓબામાએ મોદી સાથેના પોતાના વિઝન પર પ્રકાશ ફેંકતા જણાવ્યું કે &#39; મારૂં માનવું છે કે ઈતિહાસની આ ક્ષણે સમાન નિયમો અને ધોરણો ધરાવતા બધા જ રાષ્ટ્રો વચ્ચે &#39;વિન વિન ફોર્મ્યુલા&#39;નું નિર્માણ કરવાની તક છે. આપણા લોકોના ઉત્થાન માટે અમે કેન્દ્રીત છે. જોકે, કોઈ અન્યના ભોગ નહીં પણ સાથે અને એક બીજા ભેગા મળીને. વડાપ્રધાન(નરેન્દ્ર મોદી) સાથે પણ આ મુદ્દે જ ચર્ચા થઈ હતી. &#39;\n" +
            "\n" +
            "\n" +
            "અમેરિકાનું ભવિષ્ય ભારત સાથે&nbsp;\n" +
            "&nbsp;\n" +
            "\n" +
            "ઓબામાએ ભારત સાથેના અમેરિકન સંબંધોને લઈને કહ્યું કે &#39;ભારત જેવા રાષ્ટ્રોમાં &#39;ઈનોર્મસ પોટેન્સીયલ&#39; પડ્યું છે. અને ક્યારેક આપણે એ તરફ ધ્યાન નથી આપતા. પણ, હું એ તરફ ઘણું ધ્યાન આપી રહ્યો છું. કારણ કે મારું માનવું છે કે આપણી(અમેરિકાની) ભવિષ્યની સમૃદ્ધી અને સુરક્ષા આફણે 1.2 બિલિયન મહત્વકાંક્ષી ભારતીયો સાથે કઈ રીતે સંબંધ રાખીએ તેના પર નિર્ભર કરે છે. ભારતીયો આપણા મુલ્યો અને લોકશાહી આપણી સાથે શેર કરે છે. &#39;\n" +
            "\n" +
            "મોદી પણ ફરિદ ઝકારિયાને ઈન્ટરવ્યૂ આપી ચુક્યા છે... જેના અંશો વાંચવા માટે સ્લાઈડ બદલો...&nbsp;]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-02 12:03:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[VIDEO: ફક્ત મહિલાઓ માટેની પ્રથમ મસ્જિદ, પુરુષો પર પ્રતિબંધ]]></title>\n" +
            "\t<shortDescription><![CDATA[Female-only mosque opens in Los Angeles that forbids men from attending may be the first of its kind in the United States]]></shortDescription>\n" +
            "\t<description><![CDATA[લોસ એન્જલસઃ લોસ એન્જલસના બહારના વિસ્તારમાં એક એવી મસ્જિદ છે જેમાં પુરુષોના પ્રવેશ પર પ્રતિબંધ છે અને અમેરિકામાં આ પ્રકારની પ્રથમ મસ્જિદ છે. લોસ એન્જલસ ટાઇમ્સના અહેવાલો પ્રમાણે ઉદ્દાટન પર નમાજ આંતરધર્મ પીકો-યૂનિયન પ્રોજેક્ટ હેઠળ 100 મહિલાઓ એકઠી થઇ હતી. જેનો ઉદ્દેશ્ય એક એવા સ્થાન પર મહિલાઓને એકઠા કરવાનો છે જ્યાં તેઓ શીખી શકે અને અન્ય મહિલાઓને જોડી શકે.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "પાસાડેનાની કાર્ડિયોલોજીસ્ટ યાસમીન રૂહગેએ કહ્યું કે, મુસ્લિમ મહિલાઓનું કોઇ ફોરમ નથી. જ્યારે અમે મસ્જિદ જઇએ છીએ તો અમારે એક તરફ બેસવું પડે છે. એવું નથી કે અમે સમાન નથી પરંતુ અહીં તમામ મહિલાઓ હોવાને કારણે અમને એકબીજા સાથે વાત કરવાની તક મળે છે. ચીન, ચીલી અને ભારતમાં ફક્ત મહિલાઓ માટેની મસ્જિદો આવેલી છે.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "આગળની સ્લાઇડમાં જુઓઃ અમેરિકામાં મહિલાઓ માટે ખુલ્લી મુકાયેલી નવી મસ્જિદની તસવીરો]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-02 14:36:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[1500 કિમીથી આવ્યો અવાજ ‘પ્લીઝ ડોન્ટ કીલ મી’, એક કોલે બચાવી જિંદગી]]></title>\n" +
            "\t<shortDescription><![CDATA[A lucky sales call from a Las Vegas to rescue a Oregon woman who was being beaten and strangled.]]></shortDescription>\n" +
            "\t<description><![CDATA[લાસ વેગાસઃ કેટલીક વાસ્તવિક ઘટનાઓ પરથી ફિલ્મો બનતી હોય છે પરંતુ ક્યારેક ક્યારેક એવી ફિલ્મી ઘટનાઓ પણ બનતી હોય છે. આવી જ એક ઘટના અમેરિકાના લાસ વેગાસમાં સામે આવી છે. ટેલિમાર્કેટિંગ એક્ઝીક્યુટિવે હેલ્થ ડ્રિક વેચવા માટે ઓરેગોન રાજ્યના એક નંબર પર કોલ કર્યો. બંન્ને જગ્યાઓ વચ્ચે લગભગ દોઢ હજાર કિલોમીટર જેટલું અંતર છે. આ એક કોલે એક મહિલાનો જીવ બચાવ્યો નહી તો તેના પતિના હાથે તેની હત્યા થઇ ગઇ હોત.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "એમરીકેર કોલ સેન્ટરની કેમિલી મેકએલરોયે હેલ્થ ડ્રિક્સ વેચવા માટે એક નંબર પર ફોન કર્યો. આ નંબર ઓરેગોન રાજ્યના લેબનોન શહેરનો હતો. ફોન ઉઠાવવાતા હેલોને બદલે રડવાનો અવાજ આવ્યો. કેમિલી સમજી ગઇ કે કાંઇ ગડબડ છે. તેણે કોલ ચાલુ રાખી. સામેથી એક મહિલા કહી રહી હતી કે, &lsquo;પ્લીઝ ડોન્ટ કીલ મી&rsquo;. કેમિલીએ તરત જ સુપરવાઇઝર ટીના ગાર્સિયા અને કંપનીના સીઇઓ મારિયો ગોજાલેજને કોલ અંગે વાત કરી. બંન્ને જણાએ કોલ સાંભળ્યો. જેમાં એક મહિલા જિંદગીની ભીખ માંગી રહી હતી.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "સીઇઓ મારિયોએ લાસ વેગાસ પોલીસને ફોન કરવાને બદલે ઓરેગોનની પોલીસને કોલ કર્યો. તેમને મહિલાનો નંબર સહિત તમામ ઘટનાની વિગતે માહિતી આપી. નંબર ટ્રેસ કર્યા બાદ જાણવા મળ્યું કે મહિલાનું ઘર લેબનોનના કોઇ અંતરિયાળ વિસ્તારમાં આવેલું છે. ઓરેગોન પોલીસ નંબર ટ્રેસ કરી તેના સરનામા તરફ નીકળી પડી.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "મહિલાનું ઘર સૂમસામ વિસ્તારમાં હતું. ઓરેગોન પોલીસ જ્યારે ત્યાં પહોચી તો અંદરથી મહિલાની બૂમો સંભળાતી હતી. પોલીસ તરત જ ઘરમાં દાખલ થઇ ગઇ. અંદર જઇને જોયું તો એક મહિલા જમીન પર પડેલી હાલતમાં હતી. તેના ગળાને એક વ્યક્તિએ રાઇફલના બટથી દબાવી રાખ્યું હતુ અને તેને મારી રહ્યો હતો. મહિલાને મારનાર વોલ્ટર વોરેન જોન મહિલાનો પતિ હતો. તેની ધરપકડ કરવામાં આવી. મહિલાનાનું નામ જાહેર કરવામાં આવ્યું નથી. મહિલાએ જણાવ્યું કે જૉને ઓશીકાથી તેનું મોં દબાવી જાનથી મારવાની કોશીષ કરી હતી.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "આગળની સ્લાઇડમાં જુઓઃ મહિલાની જાન બચાવનારી ટીના ગાર્સિયા અને મારિયો ગોંજાલેજ]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-02 18:30:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[અમેરિકા યુક્રેનને વધુ શસ્ત્રો આપે તેવી શક્યતા]]></title>\n" +
            "\t<shortDescription><![CDATA[US President Barack Obama is reconsidering whether to send lethal assistance to Ukraine, a senior administration official said today, but continues to have conc]]></shortDescription>\n" +
            "\t<description><![CDATA[\n" +
            "(યુએસ રાષ્ટ્રપતિ બરાક ઓબામા)\n" +
            "\n" +
            "વોશિંગ્ટન: રશિયા તરફી ભાગલાવાદીઓ સામે ઝઝૂમી રહેલા યુક્રેનને અમેરિકા સૈન્ય મદદ આપવાની તૈયારી કરી રહ્યું છે. અમેરિકાના વિદેશપ્રધાન જોન કેરી ગુરુવારે યુક્રેનમાં રાષ્ટ્રપતિ પેટ્રો પોરોશેંકો અને અન્ય અધિકારીઓ સાથે આ મુદ્દા અંગે ચર્ચા કરશે.\n" +
            "&nbsp;\n" +
            "\n" +
            "જોકે, અમેરિકી રાષ્ટ્રપતિ બરાક ઓબામાએ સ્પષ્ટપણે જણાવ્યું છે કે રશિયા સાથે સૈન્ય ટકરાવ ડહાપણભર્યું નથી. બીજી બાજુ, યુક્રેનના દોનેત્સ્કમાં&nbsp; ભાગલાવાદીઓ સાથેના સંઘર્ષમાં યુક્રેનના પાંચ સૈનિકો માર્યા ગયા છે. ન્યૂયોર્ક ટાઇમ્સના અહેવાલ મુજબ અમેરિકાના આઠ ભૂતપૂર્વ વરિષ્ઠ અધિકારી સ્વતંત્ર રિપોર્ટ જારી કરશે. તેઓ યુક્રેનને ઘાતક મિસાઇલો, ડ્રોન અને ત્રણ અબજ ડોલરનાં ખતરનાક શસ્ત્રો આપવાની અમેરિકી વહીવટીતંત્રને ભલામણ કરશે. જોઇન્ટ ચીફ ઓફ સ્ટાફના ચેરમેન અને નાટોના કમાન્ડર પણ યુક્રેનને શસ્ત્રો આપવાની તરફેણમાં છે. અમેરિકાના રાષ્ટ્રીય સુરક્ષા સલાહકાર રાઇસનું માનવું હતાં કે યુક્રેનને શસ્ત્ર ન આપવાં જોઇએ.\n" +
            "&nbsp;\n" +
            "વિખૂટા પાડો પરંતુ સૈન્ય ઘર્ષણ નહીં\n" +
            "&nbsp;\n" +
            "\n" +
            "ઓબામાએ ગત સપ્તાહે યુક્રેનના પૂર્વ ભાગમાં ભાગલાવાદીઓ સાથેના સૈન્ય સંઘર્ષ અંગે ચિંતા વ્યક્ત કરી છે. તેમણે જણાવ્યું કે યુક્રેનમાં રશિયાને વિખૂટું પાડવા માટે અમેરિકા સૈન્ય કાર્યવાહી સિવાય બધા વિકલ્પો અંગે વિચાર કરી રહ્યું છે.\n" +
            "&nbsp;\n" +
            "\n" +
            "દેબાલ્ત્સેવમાં ભીષણ લડાઇ\n" +
            "&nbsp;\n" +
            "\n" +
            "&nbsp;દેબાલ્ત્સેવમાં ભાગલાવાદીઓ અને યુક્રેનના સૈન્ય વચ્ચે શનિવા અને રવિવારે ભીષણ લડાઇ થઇ છે. આશરે 25000ની વસ્તીવાળા દેબાલ્ત્સેવમાં યુક્રેનનું નિયંંત્રણ છે. દોનેત્સ્ક અને લુહાંસ્ક તરફથી ભાગલાવાદીઓએ અહીં ફાયરિંગ કર્યું. સરકારે લોકોને અહીંથી સલામત રીતે બહાર કાઢવાનું શરૂ કરી દીધું છે.\n" +
            "\n" +
            "]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-03 09:09:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[ઓબામાનું 247.20 લાખ કરોડનું બજેટ રજૂ]]></title>\n" +
            "\t<shortDescription><![CDATA[US President Barack Obama today unveiled a USD 4 trillion budget aimed at improving the nation's infrastructure and boosting middle-class Americans by increasin]]></shortDescription>\n" +
            "\t<description><![CDATA[\n" +
            "(બરાક ઓબામાની ફાઇલ તસવીર)\n" +
            "\n" +
            "રશિયા વિરુદ્ધના સંઘર્ષ માટે યુક્રેનને મદદ કરશે, મધ્યમવર્ગને કરમાં રાહત અપાઈ\n" +
            "&nbsp;\n" +
            "\n" +
            "વોશિંગ્ટનઃ અમેરિકાના રાષ્ટ્રપ્રમુખ બરાક ઓબામાએ સોમવારે સાડા ચાર લાખ કરોડ ડોલર એટલે કે અંદાજે 247.20 લાખ કરોડ રૂપિયાનું બજેટ રજૂ કર્યું હતું. તેમના જણાવ્યા અનુસાર તેમનો હેતુ ઈન્ફ્રાસ્ટ્રક્ચરમાં સુધારો તથા શ્રીમંતો અને ઉદ્યોગપતિઓ પર કર વધારીને મધ્યમવર્ગીય અમેરિકનોને રાહત આપવાનો છે.\n" +
            "&nbsp;\n" +
            "\n" +
            "આ પ્રસ્તાવને કારણે રિપબ્લિકન પાર્ટી સાથેનો તેમનો સંઘર્ષ વધુ તેજ બને તેવી શક્યતા છે. લશ્કર પાછળ 561 અબજ ડોલર એટલે કે 34.67 લાખ કરડો રૂપિયા ખર્ચાશે. તેમાં રશિયા સામે સંઘર્ષ કરી રહેલા યુક્રેનને અપાનારી મદદનો પણ સમાવેશ થાય છે. લગભગ 14 અબજ ડોલર એટલે કે 86520 કરોડ રૂપિયા સાઈબર સુરક્ષા માટે અનામત રાખવામાં આવ્યા છે.\n" +
            "]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-03 02:10:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[FB પોસ્ટ કર્યા બાળકોને શિકાર શિખાડતા Photos, મળી મોતની ધમકી]]></title>\n" +
            "\t<shortDescription><![CDATA[Female Hunter Receiving Death Threats Over Teaching children to hunt]]></shortDescription>\n" +
            "\t<description><![CDATA[કેલિફોર્નિયાઃ અમેરિકાના કેલિફોર્નિયામાં રહેતી જેન કોર્ડારો હાલમાં એનિમલ રાઈટ્સ એક્ટિવિસ્ટના નિશાન પર છે. બાળકોને શિકાર શીખાડવાને કારણે તેને મોતની ધમકીઓ મળી રહી છે. શિકારની શોખીન જેન કોર્ડારોએ યુવાનોને શિકાર શિખાડવા માટે એક પ્રોગ્રામ શરૂ કર્યો છે. ફેસબૂક પેજ પર પોતાના જેન દ આર્ચર લખનારી જેને બે મહિના પહેલા એક પ્રોગ્રામ શરૂ કર્યો હતો. જેને નામ અપાયું હતું #BringAKidHunting. આ પ્રોગ્રામ અંતર્ગત એ બાળકોને એવું શિખવાડી રહી હતી કે કઈ રીતે જવાબદારી સાથે શિકાર કરી શકાય.&nbsp;\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "જેને બાળકોને શિકાર શિખાડવાની તસવીરો પોતાના ફેસબૂક પેજ પર શેર પણ કરી છે. આ તસવીરોએ એક્ટિવિસ્ટોનું ધ્યાન જેન તરફ ખેંચ્યું હતું. જે બાદ તેને મોતની ધમકીઓ મળી રહી છે. લોકો શિકાર બંધ કરવા ઈચ્છે છે. સોશિયલ મીડિયા થકી પણ જેનને પોતાના આ પગલાને લઈને સખત આલોચનાનો સામનો કરવો પડી રહ્યો છે. જેન વિરુદ્ધ લોકોએ એક ઓનલાઈ પિટીશન પણ ફાઈલ કરી છે. જેમાં તેને ખુની બતાવતા શિકાર રોકવાની માગ કરવામાં આવી છે.&nbsp;\n" +
            "\n" +
            "\n" +
            "Change.org પિટિશનમાં જેનને આરોપી ગણાવતા કહેવાયું છે કે તે બાળકોને પ્રાણીઓની હત્યા કરવાનું શિખવાડી રહી છે. તેની ટ્રેનિંગના આ પ્રોગ્રામ થકી બાળકોમાં એવો સંદેશ જઈ રહ્યો છે કે પ્રાણીઓને મારવા એ કોઈ ખોટું કામ નથી. આ પિટિશનના સમર્થનમાં અત્યાર સુધી 2000થી વધુ લોકોએ સહીં કરી છે. આ મુદ્દે જેને કહ્યું છે કે આ પિટિશનને કારણે તે અસહજ થઈ ગઈ છે.&nbsp;\n" +
            "\n" +
            "જોકે, આ બધા વચ્ચે, જેનને કેટલીક જગ્યાએ સમર્થન પણ મળી રહ્યું છે. ખાસ કરીને એ બાળકોના માતાપિતા જેનના સમર્થનમાં ઉભા થઈ ગયાં છે, જેમને તેઓ શિકારની ટ્રેનિંગ આપી રહ્યાં છે. બીજી બાજુ, જેન પણ હવે પાછળ હટવા તૈયાર નથી. તેણે કહ્યું કે તે આવી ધમકીઓથી ડરીને ભાગવાની નથી. તે પોતાનું કામ ચાલું રાખશે.&nbsp;\n" +
            "&nbsp;\n" +
            "\n" +
            "આગળની સ્લાઈડમાં જુઓ... જેન કોર્ડારો દ્વારા બાળકોને અપાઈ રહેલી ટ્રેનિંગની તસવીરો...&nbsp;]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-03 09:13:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t\t<NewsHeadline>\n" +
            "\t<title><![CDATA[એપ્પલના CEO ટિમને 56 કરોડનું પેકેજ, તેમની જુનિયરને મળ્યા 451 કરોડ]]></title>\n" +
            "\t<shortDescription><![CDATA[Apple executive Angela Ahrendts received greater compensation in 2014 than any other Apple executive, including CEO Tim Cook.]]></shortDescription>\n" +
            "\t<description><![CDATA[\n" +
            "વોશિંગ્ટનઃ દુનિયાની સૌથી કિંમતી કંપની એપ્પલ કદાચ એકમાત્ર એવી કંપની હશે જ્યાં સીઇઓને ઓછો અને તેમના જુનિયરોને વધુ પગાર આપવામાં આવે છે. કંપનીના સીઇઓ ટીમ કૂકને 2014માં 56 કરોડ રૂપિયાનું પેકેજ મળ્યું હતું જ્યારે તેમનાથી જૂનિયર વાઇસ પ્રેસિડેન્ડ એન્જેલા એરેટ્સને 451 કરોડ રૂપિયાનું પેકેજ આપવામાં આવ્યું હતું.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "એન્જેલા એપ્પલના રિટેલ સેક્શની પ્રમુખ છે. રસપ્રદ વાત એ છે કે એપ્પલમાં એન્જેલા એકલી એવી નથી જેને સીઇઓ કરતા વધુ પગાર આપવામાં આવ્યો હોય. ઓનલાઇન યુનિટના પ્રમખ એડી ક્યૂ, સીઇઓ લૂકા મૈસ્ટ્રી અને ચીફ ઓફ ઓપરેશન્સ જેફરી વિલિયમ્સને પણ ગયા વર્ષે કૂક કરતા વધુ પગાર આપવામાં આવ્યો હતો.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "એન્જેલાઃ રેકોર્ડબ્રેક વેચાણ પાછળનો ચહેરો\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "એન્જેલા 2006માં લક્ઝરી રીટેલર બરબેરીની સીઇઓ હતી. તેણે 157 વર્ષ જૂની કંપનીની કાયાપલટ કરી. મે, 2014માં એપ્પલમાં જોડાયા. તે વર્ષે પગાર, બોનસ, સ્ટોક સહિત કુલ 451 કરોડ રૂપિયા મળ્યા. બરબેરી છોડતી વખતે તેઓને સ્ટોક ઓપ્શન કંપનીને પાછા આપવા પડ્યા હતા. એપ્પલે તેની ભરપાઇ 227 કરોડ રૂપિયાના શેર આપીને કરી હતી. એન્જેલાને પગાર સિવાય ફ્ચુચર સ્ટોક સ્વરૂપે 203 કરોડ રૂપિયાના શેર મળ્યા હતા.\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "- ચીનમાં વ્યાપારમાં 70 ટકાનો વધારો\n" +
            "\n" +
            "- 15 દેશોમાં હાઇસ્ટ્રીટ સ્ટોર અને 39 દેશોમાં ઓનલાઇન સેલ્સની જવાબદારી પણ\n" +
            "\n" +
            "- 430 કરોડ રૂપિયાના સ્ટૉક ઓપ્શન મળ્યા\n" +
            "\n" +
            "&nbsp;\n" +
            "\n" +
            "&nbsp;\n" +
            "આગળની સ્લાઇડમાં વાંચોઃ આ વર્ષે આમના પેકેજ પણ સીઇઓ કરતા વધુ]]></description>\n" +
            "\t<thumbnailImageUrl></thumbnailImageUrl>\n" +
            "\t<fullImageUrl></fullImageUrl>\n" +
            "\t<pubDate>2015-02-03 16:21:00</pubDate>\n" +
            "\t<category></category>\n" +
            "\t</NewsHeadline>\n" +
            "\t</NewsHeadlines>\t \n";

    private void doXML(String content) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        xpp.setInput(new StringReader(content));
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_DOCUMENT) {
                Log.i(TAG, "Start document");
            } else if(eventType == XmlPullParser.END_DOCUMENT) {
                Log.i(TAG, "End document");
            } else if(eventType == XmlPullParser.START_TAG) {
                Log.i(TAG, "Start tag "+ xpp.getName());
            } else if(eventType == XmlPullParser.END_TAG) {
                Log.i(TAG, "End tag "+xpp.getName());
            } else if(eventType == XmlPullParser.TEXT) {
                Log.i(TAG, "Text "+xpp.getText());
            }
            eventType = xpp.next();
        }
    }
}
