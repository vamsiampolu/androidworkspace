package tut2.calc3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
 

public class CalculateValue extends Activity {
    /** Called when the activity is first created. */
   private int op;
   private float temp;
   private  TextView inp;
   private float ans;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      
        
        inp = (TextView)findViewById(R.id.editText1);
        final Button equal = (Button)findViewById(R.id.equal);
        final Button add = (Button)findViewById(R.id.add);
        final Button div = (Button)findViewById(R.id.division);
        final Button minus = (Button)findViewById(R.id.minus);
        final Button multiply = (Button)findViewById(R.id.multiply);
        
        equal.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				float p;
				p=CalculateValue.this.getValue(inp.getText().toString()); 
				
				if(op==1) 
				      ans = temp+p;
				else if(op==2){
						  if(p>0)
							 ans = temp/p;
						   else
							 Toast.makeText(CalculateValue.this, "Divisor Should not Zero",Toast.LENGTH_SHORT).show();
				} else if(op==3){						
							ans = temp-p;					
							
				} else if(op==4){
						ans = temp*p;
				}
				inp.setText((CharSequence)String.valueOf(ans));
				temp=ans;
			
			} 
		 } );
        

        add.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				op=1; 
				temp=CalculateValue.this.getValue(inp.getText().toString());
				inp.setText("");
				
			}
		});
        div.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				op=2;
				temp=CalculateValue.this.getValue(inp.getText().toString());
				inp.setText("");
			}
		});
        

        minus.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				op=3;
				temp=CalculateValue.this.getValue(inp.getText().toString());
				inp.setText("");
			}
		});
        

        multiply.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) {
				op=4;
				temp=CalculateValue.this.getValue(inp.getText().toString());
				inp.setText("");
			}
		});
        
        
    }
    
    public float getValue(String str){
    	float p=0; 	
		if(!(str.trim().equals("")))
			p=Float.parseFloat(str);
		else 
			p=0;
    	
    	return p;
    }
  }
    
