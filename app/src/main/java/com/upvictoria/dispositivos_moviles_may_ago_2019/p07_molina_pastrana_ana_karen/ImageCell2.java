package com.upvictoria.dispositivos_moviles_may_ago_2019.p07_molina_pastrana_ana_karen;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

/**
* Esta subclase de ImageView se usa para mostrar una imagen en un GridView.
* Un ImageCell sabe qué celda de la cuadrícula está mostrando y a qué cuadrícula está conectada
* Los números de celda son de 0 a NumCells-1.
* También sabe si está vacío.
*
* <p> Las celdas de imagen son lugares donde las imágenes se pueden arrastrar y soltar.
* Por lo tanto, esta clase implementa las interfaces DragSourceOLD y DropTargetOLD.
*
*/
public class ImageCell2 extends ImageView
    implements DragSource, DropTarget
{
    public boolean mEmpty = true;
    public int mCellNumber = -1;
    public GridView mGrid;

/**
 */
// Constructors

public ImageCell2(Context context) {
	super (context);
}
public ImageCell2(Context context, AttributeSet attrs) {
	super (context, attrs);
}
public ImageCell2(Context context, AttributeSet attrs, int style) {
	super (context, attrs, style);
}

/**
 */
// DragSource interface methods

/**
 * Se llama a este método para determinar si DragSourceOLD tiene algo que arrastrar.
 *
 * @return True si hay algo que arrastrar
 */

public boolean allowDrag () {
    return !mEmpty;
}

public ClipData clipDataForDragDrop () { return null; }

/**
 * Devuelve la vista que es la fuente real de la información que se está arrastrando.
 * Dado que ImageCell implementa la interfaz DragSource, es la vista en sí misma.
 * 
 * @return View
 */

public View dragDropView () {
    return this;
}

/**
 * Este método se llama al inicio de una operación de arrastrar y soltar para que el objeto que está siendo
 * Arrastrado sabe que está siendo arrastrado. *
 */

public void onDragStarted () {
    if (mCellNumber >= 0) {
        setColorFilter(R.color.cell_nearly_empty);
        invalidate();
    }
}

/**
 * This method is called on the completion of the drag operation so the DragSource knows 
 * whether it succeeded or failed.
 * 
 * @param target DropTarget - the object that accepted the dragged object
 * @param success boolean - true means that the object was dropped successfully
 */

public void onDropCompleted (DropTarget target, boolean success) {
    // Undo what we did in onDragStarted
    if (mCellNumber >= 0) {
       clearColorFilter ();
       invalidate ();
    }

    // If the drop succeeds, the image has moved elsewhere. 
    // So clear the image cell.

    if (success) {
       Log.d (DragActivity2.LOG_NAME, "ImageCell2.onDropCompleted - clearing source: " + mCellNumber);
       mEmpty = true;
       if (mCellNumber >= 0) {
          int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
          setBackgroundResource (bg);
          setImageDrawable (null);
       } else {
            // Si el número de celda es negativo, significa que estamos interactuando con un autónomo
           // celda de imagen. Hay uno de esos. Es el lugar donde se agrega una imagen cuando
           // el usuario hace clic en "agregar imagen".
           // En la conclusión de una gota, borrarlo.
         setImageResource (0);
       }
    } else {
      // On the failure case, reset the background color in case it is still set to the hover color.
      if (mCellNumber >=0) {
         int bg2 = mEmpty ? R.color.cell_empty : R.color.cell_filled;
         setBackgroundResource (bg2);
      }
    }

}

/**
 */
// DropTarget interface methods

/**
 * Devuelve verdadero si el DropTarget permite que se coloquen objetos sobre él.
 * No permitir la caída si el objeto fuente es el mismo ImageCell.
 * Permitir que una gota de la ImageCell esté vacía.
 *
 * @param source DragSource donde comenzó el arrastre
 * @return booleano Verdadero si se aceptará la caída, falso en caso contrario. */

public boolean allowDrop (DragSource source) {
    // Do not allow a drop if the DragSource is the same cell.
    if (source == this) return false;

    // Un ImageCell acepta una caída si está vacía y si es parte de una cuadrícula.
    // Un ImageCell independiente no acepta gotas.
    return mEmpty  && (mCellNumber >= 0);
}

/**
 * Manejar un objeto que se cae en el DropTarget
 * 
 * @param source DragSource where the drag started
 */

public void onDrop (DragSource source) {
    Log.d (DragActivity2.LOG_NAME, "/////ONDROP : " + mCellNumber + " ////SOURCE: " + source);
        
    // Mark the cell so it is no longer empty.
    mEmpty = false;
    int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
    setBackgroundResource (bg);

    // La vista que se está arrastrando no cambia realmente su padre y cambia a ImageCell.
    // Lo que hacemos es copiar el dibujo desde la vista de origen.
    ImageView sourceView = (ImageView) source.dragDropView ();
    Drawable d = sourceView.getDrawable ();

    if (d != null) {
       this.setImageDrawable (d);
       this.invalidate ();
    } else {
      Log.e (DragActivity2.LOG_NAME, "ImageCell2.onDrop. Null Drawable");
    }

    //
    try {

        //no. 1
        int id = getResources().getIdentifier("@drawable/uno", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateUno;

        constantStateUno = getResources().getDrawable(id).getConstantState();

        //no. 2
        id = getResources().getIdentifier("@drawable/dos", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateDos;

        constantStateDos = getResources().getDrawable(id).getConstantState();

        //no. 3
        id = getResources().getIdentifier("@drawable/tres", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateTres;

        constantStateTres = getResources().getDrawable(id).getConstantState();

        //no. 4
        id = getResources().getIdentifier("@drawable/cuatro", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateCuatro;

        constantStateCuatro = getResources().getDrawable(id).getConstantState();

        //no. 5
        id = getResources().getIdentifier("@drawable/cinco", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateCinco;

        constantStateCinco = getResources().getDrawable(id).getConstantState();

        //no. 6
        id = getResources().getIdentifier("@drawable/seis", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateSeis;

        constantStateSeis = getResources().getDrawable(id).getConstantState();

        //no. 7
        id = getResources().getIdentifier("@drawable/siete", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateSiete;

        constantStateSiete = getResources().getDrawable(id).getConstantState();

        //no. 8
        id = getResources().getIdentifier("@drawable/ocho", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateOcho;

        constantStateOcho = getResources().getDrawable(id).getConstantState();

        //no. 9
        id = getResources().getIdentifier("@drawable/nueve", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateNueve;

        constantStateNueve = getResources().getDrawable(id).getConstantState();

        //no. 10
        id = getResources().getIdentifier("@drawable/diez", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateDiez;

        constantStateDiez = getResources().getDrawable(id).getConstantState();

        //no. 11
        id = getResources().getIdentifier("@drawable/once", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateOnce;

        constantStateOnce = getResources().getDrawable(id).getConstantState();

        //no. 12
        id = getResources().getIdentifier("@drawable/doce", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateDoce;

        constantStateDoce = getResources().getDrawable(id).getConstantState();

        //no. 13
        id = getResources().getIdentifier("@drawable/trece", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateTrece;

        constantStateTrece = getResources().getDrawable(id).getConstantState();

        //no. 14
        id = getResources().getIdentifier("@drawable/catorce", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateCatorce;

        constantStateCatorce = getResources().getDrawable(id).getConstantState();

        //no. 15
        id = getResources().getIdentifier("@drawable/quince", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateQuince;

        constantStateQuince = getResources().getDrawable(id).getConstantState();

        //no. 16
        id = getResources().getIdentifier("@drawable/dieciseis", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateDieciseis;

        constantStateDieciseis = getResources().getDrawable(id).getConstantState();

        //no. 17
        id = getResources().getIdentifier("@drawable/diecisiete", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateDiecisiete;

        constantStateDiecisiete = getResources().getDrawable(id).getConstantState();

        //no. 18
        id = getResources().getIdentifier("@drawable/dieciocho", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateDieciocho;

        constantStateDieciocho = getResources().getDrawable(id).getConstantState();


        //no. 19
        id = getResources().getIdentifier("@drawable/diecinueve", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateDiecinueve;

        constantStateDiecinueve = getResources().getDrawable(id).getConstantState();

        //no. 20
        id = getResources().getIdentifier("@drawable/veinte", null, DragActivity2.PACKAGE_NAME);
        Drawable.ConstantState constantStateVeinte;

        constantStateVeinte = getResources().getDrawable(id).getConstantState();


        Log.d("R", "Antes de if-----------------------------------------------------------------------------------------");
        if (d.getConstantState() == constantStateUno) {
            DragActivity2.respuesta = "1";
            Log.d("R", "EsUno: " + DragActivity2.respuesta);
        } else if(d.getConstantState() == constantStateDos){
            DragActivity2.respuesta = "2";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateTres){
            DragActivity2.respuesta = "3";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateCuatro){
            DragActivity2.respuesta = "4";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateCinco){
            DragActivity2.respuesta = "5";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateSeis){
            DragActivity2.respuesta = "6";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }   else if(d.getConstantState() == constantStateSiete){
            DragActivity2.respuesta = "7";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateOcho){
            DragActivity2.respuesta = "8";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateNueve){
            DragActivity2.respuesta = "9";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }   else if(d.getConstantState() == constantStateDiez){
            DragActivity2.respuesta = "10";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }   else if(d.getConstantState() == constantStateOnce){
            DragActivity2.respuesta = "11";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }   else if(d.getConstantState() == constantStateDoce){
            DragActivity2.respuesta = "12";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }   else if(d.getConstantState() == constantStateTrece){
            DragActivity2.respuesta = "13";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateCatorce){
            DragActivity2.respuesta = "14";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateQuince){
            DragActivity2.respuesta = "15";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateDieciseis){
            DragActivity2.respuesta = "16";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateDiecisiete){
            DragActivity2.respuesta = "17";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }   else if(d.getConstantState() == constantStateDieciocho){
            DragActivity2.respuesta = "18";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }   else if(d.getConstantState() == constantStateDiecinueve){
            DragActivity2.respuesta = "19";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }  else if(d.getConstantState() == constantStateVeinte){
            DragActivity2.respuesta = "20";
            Log.d("R", "EsCero: " + DragActivity2.respuesta);
        }

    }catch (Exception e){
        System.out.println("Existe una excepciooooooooooooooooooooooooooooooooooooooooooooooooon "+e);
    }
}

/**
 * Reaccione ante un objeto arrastrado entrando en la vista del DropTarget.
 */

public void onDragEnter (DragSource source) {
    if (mCellNumber < 0) return;
    int bg = mEmpty ? R.color.cell_empty_hover : R.color.cell_filled_hover;
    setBackgroundResource (bg);
}

/**
 * React to a dragged object leaving the view of the DropTarget.
 */    

public void onDragExit (DragSource source) {
    if (mCellNumber < 0) return;
    int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
    setBackgroundResource (bg);
}

/**
 */
// Other Methods

/**
 * Devuelve verdadero si esta celda está vacía.
 * Si lo es, significa que aceptará vistas caídas.
 * También significa que no hay nada que arrastrar.
 *
 * @return boolean
 * */

public boolean isEmpty () { return mEmpty; }

/**
 * Llame a esta vista en el oyente onClick. Devuelve true si fue llamado.
 * Los clics se ignoran si la celda está vacía.
 *
 * @return boolean
 */

public boolean performClick ()
{
    if (!mEmpty) return super.performClick ();
    return false;
}

/**
 * Llame al oyente onLongClick de esta vista. Devuelve true si fue llamado.
 * Los clics se ignoran si la celda está vacía.
 * @return boolean
 */

public boolean performLongClick ()
{
    if (!mEmpty) return super.performLongClick ();
    return false;
}

} // end ImageCell
