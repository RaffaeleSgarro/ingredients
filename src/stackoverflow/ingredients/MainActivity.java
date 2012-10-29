package stackoverflow.ingredients;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private IngredientsView ingredients;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.save).setOnClickListener(saveListener);
        ingredients = (IngredientsView) findViewById(R.id.ingredients);
    }
    
    private OnClickListener saveListener = new OnClickListener() {

		public void onClick(View button) {
			
			Toast.makeText(MainActivity.this, "Saving... Check the logs", Toast.LENGTH_LONG).show();
			
			for (String ingredient : ingredients.getIngredients()) {
				Log.v("INGREDIENTS", ingredient);
			}
			
		}
		
    };
    
    public static class IngredientsView extends LinearLayout {

    	public IngredientsView(Context context) {
			super(context);
			setup();
		}
		
		public IngredientsView(Context context, AttributeSet attrs) {
			super (context, attrs);
			setup();
		}
		
		private void setup() {
			setOrientation(VERTICAL);
			addRow();
		}
    	
		private OnClickListener btnListener = new OnClickListener() {

			public void onClick(View view) {
				
				Button btn = (Button) view;	
				int index = indexOfChild((View) btn.getParent());
				
				if (index == getChildCount() - 1) {
					addRow();
					btn.setText("-");
				} else {
					removeViewAt(index);
				}
			}
		};
		
		private void addRow() {
			View row = inflate(getContext(), R.layout.ingredients_item, null);
			
			Button button = (Button) row.findViewById(R.id.button);
			button.setOnClickListener(btnListener);
			button.setText("+");
			
			addView(row);
			
			row.findViewById(R.id.ingredient).requestFocus();
		}
		
		public List<String> getIngredients() {
			List<String> ingredients = new LinkedList<String>();
			
			for (int i = 0; i < getChildCount(); i++) {
				ingredients.add(((TextView) getChildAt(i).findViewById(R.id.ingredient)).getText().toString());
			}
			
			return ingredients;
		}
    }
    
}
