package com.tzuyen.CDHCalculator.playerDataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class CDHPlayerData {

    private static final String CDHPLAYER_FILE = "CDHPLAYER.xml";
    private static final String CDHPLAYER = "CDHPlayer";
    private static final String PLAYER_NAME = "Player_name";
    private static final String SCORE_8_FINAL = "Score_8_final";
    private static final String SCORE_9_FINAL = "Score_9_final";
    private static final String SCORE_10_FINAL = "Score_10_final";
    private static final String SCORE_11_FINAL = "Score_11_final";
    private static final String SCORE_12_FINAL = "Score_12_final";
    private static final String SCORE_13_FINAL = "Score_13_final";
    private static final String SCORE_14_FINAL = "Score_14_final";
    private static final String SCORE_15_FINAL = "Score_15_final";
    private static final String SCORE_16_FINAL = "Score_16_final";
    private static final String SCORE_TOTAL_FINAL = "Score_Total_final";
    private ObservableList<CDHPlayer> CDHPlayers;

    public CDHPlayerData() {
        CDHPlayers = FXCollections.observableArrayList();
    }

    public ObservableList<CDHPlayer> getCDHPlayers(){
        return CDHPlayers;
    }

    public void addCDHPlayers(CDHPlayer player){
        CDHPlayers.add(player);
    }

    public void deleteCDHPlayers(CDHPlayer player){
        CDHPlayers.remove(player);
    }

    public void loadCDHPlayer() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(CDHPLAYER_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            CDHPlayer Player = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a CDHPlayer item, we create a new contact
                    if (startElement.getName().getLocalPart().equals(CDHPLAYER)) {
                        Player = new CDHPlayer();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(PLAYER_NAME)) {
                            event = eventReader.nextEvent();
                            Player.setPlayerName(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_8_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_8(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_9_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_9(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_10_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_10(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_11_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_11(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_12_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_12(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_13_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_13(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_14_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_14(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_15_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_15(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_16_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_16(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCORE_TOTAL_FINAL)) {
                        event = eventReader.nextEvent();
                        Player.setScore_Total(Double.parseDouble(event.asCharacters().getData()));
                        continue;
                    }
                }

                // If we reach the end of a Player element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(CDHPLAYER)) {
                        CDHPlayers.add(Player);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveCDHPlayers() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(CDHPLAYER_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement playersStartElement = eventFactory.createStartElement("",
                    "", "CDHPlayers");
            eventWriter.add(playersStartElement);
            eventWriter.add(end);

            for (CDHPlayer player: CDHPlayers) {
                saveCDHPlayer(eventWriter, eventFactory, player);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "CDHPlayers"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Players file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing Players: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveCDHPlayer(XMLEventWriter eventWriter, XMLEventFactory eventFactory, CDHPlayer player)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create player open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", CDHPLAYER);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, PLAYER_NAME, player.getPlayerName());
        createNode(eventWriter, SCORE_8_FINAL, player.getScore_8()+"");
        createNode(eventWriter, SCORE_9_FINAL, player.getScore_9()+"");
        createNode(eventWriter, SCORE_10_FINAL, player.getScore_10()+"");
        createNode(eventWriter, SCORE_11_FINAL, player.getScore_11()+"");
        createNode(eventWriter, SCORE_12_FINAL, player.getScore_12()+"");
        createNode(eventWriter, SCORE_13_FINAL, player.getScore_13()+"");
        createNode(eventWriter, SCORE_14_FINAL, player.getScore_14()+"");
        createNode(eventWriter, SCORE_15_FINAL, player.getScore_15()+"");
        createNode(eventWriter, SCORE_16_FINAL, player.getScore_16()+"");
        createNode(eventWriter, SCORE_TOTAL_FINAL, player.getScore_Total()+"");

        eventWriter.add(eventFactory.createEndElement("", "", CDHPLAYER));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Player
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}
