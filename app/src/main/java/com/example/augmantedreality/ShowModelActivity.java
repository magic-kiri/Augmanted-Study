package com.example.augmantedreality;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ShowModelActivity extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable skullRenderable,
                            skeletonRenderable,
                            ribCageRenderable,
                            maleLowRenderable,
                            hepatitisLiverRenderable,
                            femurRenderable,
                            teethRenderable,
                            brainRenderable;

    ImageView skullImage,skeletonImage,maleLawImage,ribCageImage,femurImage,hepatitisLiverImage,teethImage,brainImage;

    View arrayView[];
    ViewRenderable name_animal;

    int selected = 1; //default skull is chosen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_model);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);

        //view
        skullImage      = findViewById(R.id.id_skull);
        skeletonImage   = findViewById(R.id.id_skeleton);
        ribCageImage    = findViewById(R.id.id_ribcage);
        //maleLawImage    = findViewById((R.id.id_maleLow));
        femurImage      = findViewById(R.id.id_femur);
        hepatitisLiverImage = findViewById(R.id.id_hepatitisLiver);
        brainImage = findViewById(R.id.id_brain);
        teethImage = findViewById(R.id.id_teeth);


        setArrayView();
        setClickListener();
        
        setupModel();
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                //tap on plane action
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());

                createModel(anchorNode,selected);
            }
        });
    }

    private void setupModel() {
        ModelRenderable.builder()
                .setSource(this,R.raw.skull)
                .build().thenAccept(renderable -> skullRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable to load model",Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this,R.raw.skeleton)
                .build().thenAccept(renderable -> skeletonRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable to load model",Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this,R.raw.femur)
                .build().thenAccept(renderable -> femurRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable to load model",Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this,R.raw.brain_model)
                .build().thenAccept(renderable -> brainRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable to load model",Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this,R.raw.teeth)
                .build().thenAccept(renderable -> teethRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this,"Unable to load model",Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
//
//        ModelRenderable.builder()
//                .setSource(this,R.raw.hepatitisliver)
//                .build().thenAccept(renderable -> hepatitisLiverRenderable = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast.makeText(this,"Unable to load model",Toast.LENGTH_SHORT).show();
//                            return null;
//                        }
//                );


    }

    private void createModel(AnchorNode anchorNode, int selected) {

        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());

        if(selected == 1)
        {
            transformableNode.getScaleController().setMaxScale(.3f);
            transformableNode.getScaleController().setMinScale(.1f);
            transformableNode.setParent(anchorNode);
            transformableNode.setRenderable(skullRenderable);
            transformableNode.select();
        }
        else if(selected == 2)
        {
            transformableNode.getScaleController().setMaxScale(.2f);
            transformableNode.getScaleController().setMinScale(.05f);
            transformableNode.setParent(anchorNode);
            transformableNode.setRenderable(skeletonRenderable);
            transformableNode.select();
        }
        else if(selected == 3)
        {
            transformableNode.getScaleController().setMaxScale(.2f);
            transformableNode.getScaleController().setMinScale(.05f);
            transformableNode.setParent(anchorNode);
            transformableNode.setRenderable(femurRenderable);
            transformableNode.select();
        }
        else if(selected == 4)
        {
            transformableNode.getScaleController().setMaxScale(.3f);
            transformableNode.getScaleController().setMinScale(.1f);
            transformableNode.setParent(anchorNode);
            transformableNode.setRenderable(brainRenderable);
            transformableNode.select();
        }
        else if(selected == 5)
        {
            transformableNode.getScaleController().setMaxScale(2f);
            transformableNode.getScaleController().setMinScale(.51f);
            transformableNode.setParent(anchorNode);
            transformableNode.setRenderable(teethRenderable);
            transformableNode.select();
        }
    }

    private void setClickListener() {
        for(int i=0 ; i< arrayView.length ; i++)
            arrayView[i].setOnClickListener(this);
    }

    private void setArrayView() {

        arrayView = new View[]{
                skullImage,skeletonImage,femurImage,brainImage,teethImage,ribCageImage,hepatitisLiverImage
        };
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_skull)
        {
            selected = 1;
            setBackground(view.getId());
        }
        else if(view.getId() == R.id.id_skeleton)
        {
            selected = 2;
            setBackground(view.getId());
        }
        else if(view.getId() == R.id.id_femur)
        {
            selected = 3;
            setBackground(view.getId());
        }
        else if(view.getId() == R.id.id_brain)
        {
            selected = 4;
            setBackground(view.getId());
        }
        else if(view.getId() == R.id.id_teeth)
        {
            selected = 5;
            setBackground(view.getId());
        }
    }

    private void setBackground(int id) {
        for(int i=0 ; i<arrayView.length ; i++)
        {
            if(arrayView[i].getId() == id )
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"));
            else
                arrayView[i].setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
