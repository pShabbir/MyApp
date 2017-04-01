package com.vissionarray.shabbirhussain.newchilsacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutNCPCR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_ncpcr);
        TextView txt=(TextView)findViewById(R.id.aboutNCPCR);

        txt.setText("The National Commission for Protection of Child Rights (NCPCR) emphasizes the principle of universality and inviolability of child rights and recognizes the tone of urgency in all the child related policies of the country. For the Commission, protection of all children in the 0 to 18 years age group is of equal importance. Thus, policies define priority actions for the most vulnerable children. This includes focus on regions that are backward or on communities or children under certain circumstances, and so on. The NCPCR believes that while in addressing only some children, there could be a fallacy of exclusion of many vulnerable children who may not fall under the defined or targeted categories. In its translation into practice, the task of reaching out to all children gets compromised and a societal tolerance of violation of child rights continues. This would in fact have an impact on the program for the targeted population as well. Therefore, it considers that it is only in building a larger atmosphere in favour of protection of children's rights, that children who are targeted become visible and gain confidence to access their entitlements.\n" +
                "\n" +
                "Likewise, for the Commission, every right the child enjoys is seen as mutually-reinforcing and interdependent. Therefore the issue of gradation of rights does not arise. A child enjoying all her rights at her 18th year is dependent on the access to all her entitlements from the time she is born. Thus policies interventions assume significance at all stages. For the Commission, all the rights of children are of equal importance.\n" +
                "\n" +
                "Organization Structure \n" +
                "Constitution \n" +
                "Functions and Powers \n" +
                "Administration and Finance \n" +
                "Responsibilities under other Acts ");
    }
}
