package vues;

import cahierIG.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.CahierException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import outils.ImageFileUtils;
import vues.controlleurs.ControlleurPageDeGarde;
import vues.controlleurs.ControlleurPageJour;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

public class PanneauDeControle {

    Cahier cahier;
    Stage stage;
    public ControlleurPageJour controlleurPageJour;
    public PanneauDeControle(Cahier c, Stage stage)
    {
        cahier = c;
        this.stage = stage;

    }

    /**
     * Passe la page courante à la page suivante
     */
    public void jourSuivant()
    {
        cahier.jourSuivant();
    }

    /**
     * Passe la page courante du cahier à la page précédente
     */
    public void jourPrecedent()
    {
        cahier.jourPrecedent();
    }

    /**
     * Passe la page courante à celle passée en paramètre
     * @param date page à regarder
     */
    public void choisirPage(DateCahier date)
    {
        try {
            cahier.changerPage(date);
        } catch (CahierException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Change le style du cahier
     */
    public void changerStyle()
    {

    }

    /**
     * Ajoute la node à l'emplacement séléctionné
     * @param type type de la node à ajouter
     */
    public void ajouterNode(String type)
    {

    }

    /**
     * Supprime la node séléctionnée
     */
    public void suppprimerNode()
    {

    }

    public void retourPageDeGarde()
    {
        cahier.setCourante(null);
        final URL url = getClass().getResource("/fxml/PDG_Carnet.fxml");
        // Création du loader.
        final FXMLLoader fxmlLoader = new FXMLLoader(url);


        ControlleurPageDeGarde controlleurPageDeGarde = new ControlleurPageDeGarde(cahier, this);
        ControlleurPageJour controlleurPageJour = new ControlleurPageJour(cahier, this,stage);


        fxmlLoader.setControllerFactory(ic-> {
            if(ic.equals(vues.controlleurs.ControlleurPageDeGarde.class)) return controlleurPageDeGarde;
            else if(ic.equals(vues.controlleurs.ControlleurPageJour.class)) return controlleurPageJour;
            else return null;
        });


        final BorderPane root;
        try {
            root = fxmlLoader.load();
            final Scene scene = new Scene(root, 600, 700);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        cahier.notifierObservateurs();
    }

    /**
     * Si elle n'existe pas, ajoute une nouvelle date
     */
    public void ajouterPage(DateCahier date, String titre) throws CahierException {
        cahier.ajouterPage(date,titre);
        chargerPageActuelle();
    }

    public void chargerPageActuelle()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/PageJour.fxml"));
        loader.setControllerFactory(ic -> {
            if (ic.equals(vues.controlleurs.ControlleurPageJour.class)) return controlleurPageJour;
            return null;
        });

        final BorderPane root;
        try {
            root = loader.load();
            final Scene scene = new Scene(root, 600, 700);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Méthode qui permet de retirer un participant de la liste
     * @param id participant à retirer
     * @throws CahierException vérifie que le participant est bien présent dans la liste
     */
    public void supprimerParticipant(String id) throws CahierException
    {
        if(!cahier.getParticipants().contains(id))
        {
            throw new CahierException("Vous tentez de supprimer un participant " + id + " non présent dans la liste");
        }else
        {
            cahier.getParticipants().remove(id);
        }
    }

    /**
     * Ajoute un nouveau participant
     * @param nom nom du participant à ajouter
     */
    public void ajouterParticipant(String nom)
    {
        cahier.ajouterParticipant(nom);
    }

    public void sauvegarderMonde() throws CahierException
    {
        Gson gson = new Gson();


        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisissez un répertoire");

        File selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            System.out.println("Répertoire sélectionné : " + selectedDirectory.getAbsolutePath());

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Nom du nouveau répertoire");
            dialog.setHeaderText("Créer un nouveau répertoire");
            dialog.setContentText("Veuillez entrer le nom du nouveau répertoire:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> {
                File newDirectory = new File(selectedDirectory, name);
                File imagesDirectory = new File(newDirectory, "images");

                if (!newDirectory.exists()) {
                    if (newDirectory.mkdir()) {
                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Répertoire créé", "Le répertoire " + newDirectory.getAbsolutePath() + " a été créé avec succès.");
                        File file = new File(newDirectory, "sauv.json");
                        if(!imagesDirectory.exists())
                        {
                            if(imagesDirectory.mkdir())
                            {
                                System.out.println("Répértoire images crée");
                            }
                        }
                        try
                        {
                            FileWriter fileWriter = new FileWriter(file);
                            BufferedWriter ecrire = new BufferedWriter(fileWriter);

                            //Auteur
                            ecrire.write(gson.toJson(cahier.getAuteur()));
                            ecrire.newLine();

                            ecrire.write(gson.toJson(cahier.getMinimum().toString()));
                            ecrire.newLine();

                            ecrire.write(gson.toJson(cahier.getMaximum().toString()));
                            ecrire.newLine();

                            //Tous les participants
                            ecrire.write(gson.toJson(cahier.getParticipants().size()));
                            ecrire.newLine();
                            for(String s : cahier.getParticipants())
                            {
                                ecrire.write(gson.toJson(s));
                                ecrire.newLine();
                            }

                            //Toutes les pages

                            ecrire.write(gson.toJson(this.cahier.getPages().size()));
                            ecrire.newLine();
                            int indice = 0;
                            for(PageIG e : this.cahier.getPages())
                            {

                                ecrire.write(gson.toJson(e.getDate().toString()));
                                ecrire.newLine();
                                ecrire.write(gson.toJson(e.getTitre()));
                                ecrire.newLine();


                                //smallNodeLeftBottom
                                for (NodeIG n : e) {
                                    if (n != null) {
                                        if (n.estTexte()) {
                                            NodeTexteIG nt = (NodeTexteIG) n;
                                            ecrire.write(gson.toJson('t'));
                                            ecrire.newLine();
                                            ecrire.write(gson.toJson(nt.getTexte()));
                                            ecrire.newLine();
                                        }
                                        if (n.estImage()) {
                                            NodeImageIG nt = (NodeImageIG) n;
                                            ecrire.write(gson.toJson('i'));
                                            ecrire.newLine();
                                            String pathToImage = imagesDirectory.getAbsolutePath()+"/image"+indice+".png";
                                            ecrire.write(gson.toJson(pathToImage));
                                            ecrire.newLine();
                                            ImageFileUtils.saveImageToFile(nt.getImage(),pathToImage);
                                        }
                                    }else
                                    {
                                        ecrire.write(gson.toJson('n'));
                                        ecrire.newLine();
                                    }
                                    indice++;
                                }

                            }

                            ecrire.close();
                        }
                        catch(IOException e)
                        {
                            throw new RuntimeException(e);
                        }
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur de création", "Le répertoire " + newDirectory.getAbsolutePath() + " n'a pas pu être créé.");
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Avertissement", "Répertoire existant", "Le répertoire " + newDirectory.getAbsolutePath() + " existe déjà.");
                }
            });

        } else {
            System.out.println("Aucun répertoire sélectionné");
            throw new CahierException("Problème: Un répértoire n'a pas été séléctioné");
        }



    }

    public void chargerMonde()
    {
        Cahier cahier = new Cahier();
        Gson gson = new Gson();

        File file;



        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisissez un répertoire");

        File selectedDirectory = directoryChooser.showDialog(stage);

        if(selectedDirectory != null) {

            // Show open file dialog
            file = new File(selectedDirectory, "sauv.json");
            if(file.exists()) {
                try (FileReader reader = new FileReader(file)) {

                    File imagesDirectory = new File(selectedDirectory, "images");

                    BufferedReader lire = new BufferedReader(reader);

                    String auteur = gson.fromJson(lire.readLine(), String.class);
                    String minimum = gson.fromJson(lire.readLine(), String.class);
                    String maximum = gson.fromJson(lire.readLine(), String.class);


                    ArrayList<String> participants = new ArrayList<>();
                    int nbParticipants = gson.fromJson(lire.readLine(), int.class);
                    for(int i =0 ; i < nbParticipants; i++)
                    {
                        participants.add(gson.fromJson(lire.readLine(), String.class));
                    }



                    int nbPages = gson.fromJson(lire.readLine(), int.class);
                    int indice = 0;
                    for(int i =0 ; i < nbPages; i++)
                    {

                        PageIG page = new PageIG();

                        String dateDuJour = gson.fromJson(lire.readLine(), String.class);
                        String titre = gson.fromJson(lire.readLine(), String.class);

                        page.setTitre(titre);
                        page.setDate(dateDuJour);
                        int compteur = 2;
                        for(NodeIG n : page)
                        {
                            System.out.println("Parcours node");
                            char typeNode = gson.fromJson(lire.readLine(), char.class);


                            if(typeNode == 't')
                            {
                                System.out.println("Chargement texte");
                                NodeTexteIG node = new NodeTexteIG(gson.fromJson(lire.readLine(), String.class));
                                System.out.println(node.getTexte());
                                page.setNodeIG(node,compteur);
                            }else if(typeNode == 'i')
                            {
                                System.out.println("Chargement image");
                                //String pathToImage = imagesDirectory.getAbsolutePath()+"/image"+indice+".png";
                                String pathToImage = gson.fromJson(lire.readLine(), String.class);
                                Image img = ImageFileUtils.loadImageFromFile(pathToImage);
                                NodeImageIG node = new NodeImageIG(ImageFileUtils.loadImageFromFile(pathToImage));

                                page.setNodeIG(node,compteur);

                            }
                            compteur++;
                            indice++;
                        }
                        cahier.ajouterPage(page);
                    }


                    lire.close();
                    for(String par : participants)
                    {
                        cahier.ajouterParticipant(par);
                    }
                    cahier.setMinimum(new DateCahier(minimum));
                    cahier.setMaximum(new DateCahier(maximum));
                    cahier.setAuteur(auteur);
                    this.cahier.chargerCahier(cahier);
                    this.cahier = cahier;
                    this.retourPageDeGarde();
                    showAlert(Alert.AlertType.CONFIRMATION, "Succés","Chargé avec succés!", "C'est bon, vous pouvez lire votre carnet de voyage");
                } catch (IOException | CahierException e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur de Chargement", e.getMessage());
                }
            }else
            {
                showAlert(Alert.AlertType.ERROR, "Erreur","Erreur de chargement","Le répértoire "+ selectedDirectory.getAbsolutePath() +" ne correspond pas à une sauvegarde de carnet");
            }


        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
