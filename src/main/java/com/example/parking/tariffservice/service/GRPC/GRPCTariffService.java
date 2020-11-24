package com.example.parking.tariffservice.service.GRPC;

import com.example.parking.tariffservice.model.CoOwner;
import com.example.parking.tariffservice.model.Tariff;
import com.example.parking.tariffservice.repositories.CoOwnersRepository;
import com.example.parking.tariffservice.repositories.TariffRepository;
import com.example.parking.tariffservice.service.TariffServiceImpl;
import com.tariffservice.grpc.TariffServiceGrpc;
import com.tariffservice.grpc.TariffServiceOuterClass;
import io.grpc.stub.StreamObserver;
import javassist.NotFoundException;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@GRpcService
@Service

public class GRPCTariffService extends TariffServiceGrpc.TariffServiceImplBase {
    @Autowired
    private TariffRepository tariffRepository;
    @Autowired
    CoOwnersRepository coOwnersRepository;

    @Override
    public void grpcGetTariff(TariffServiceOuterClass.Id request, StreamObserver<TariffServiceOuterClass.Tariff> responseObserver) {
        int id = request.getId();
        Optional<Tariff> tempTariff = tariffRepository.findById(id);
        Tariff tariff;
        TariffServiceOuterClass.Tariff.Builder response =
                TariffServiceOuterClass.Tariff.newBuilder();
        if(tempTariff.isPresent())
        {
            tariff = tempTariff.get();
            response.setId(tariff.getTariffId());
            response.setElectricityTariff(tariff.getElectricityTariff());
            response.setGasTariff(tariff.getGasTariff());
            response.setWaterTariff(tariff.getWaterTariff());
            response.setPlacePrice(tariff.getPlacePrice());
            response.setTariffDesc(tariff.getTariffDesc());
        }
        else
        {
            responseObserver.onError(new Exception());
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void grpcGetAllTariffs(TariffServiceOuterClass.Empty request, StreamObserver<TariffServiceOuterClass.Tariffs> responseObserver) {
        List<Tariff> tariffs = tariffRepository.findAll();
        TariffServiceOuterClass.Tariffs.Builder tariffsResponse=
                TariffServiceOuterClass.Tariffs.newBuilder();
        for(Tariff t:tariffs)
        {
            TariffServiceOuterClass.Tariff.Builder tariffToList=
                    TariffServiceOuterClass.Tariff.newBuilder();
            tariffToList.setId(t.getTariffId());
            tariffToList.setElectricityTariff(t.getElectricityTariff());
            tariffToList.setGasTariff(t.getGasTariff());
            tariffToList.setWaterTariff(t.getWaterTariff());
            tariffToList.setPlacePrice(t.getPlacePrice());
            tariffToList.setTariffDesc(t.getTariffDesc());
            tariffsResponse.addTariffs(tariffToList);
        }
        responseObserver.onNext(tariffsResponse.build());
        responseObserver.onCompleted();
        }

    @Override
    public void grpcUpdateTariff(TariffServiceOuterClass.Tariff request, StreamObserver<TariffServiceOuterClass.Empty> responseObserver) {
        int id = request.getId();
        double placePrice = request.getPlacePrice();
        double gas = request.getGasTariff();
        double water = request.getWaterTariff();
        double electricity = request.getElectricityTariff();
        String description = request.getTariffDesc();
        Tariff updateTariff = new Tariff(placePrice,water,electricity,gas,description);
        updateTariff.setTariffId(id);
        if(tariffRepository.existsById(id))
        {
            tariffRepository.save(updateTariff);
        }
        TariffServiceOuterClass.Empty.Builder response =
                TariffServiceOuterClass.Empty.newBuilder();
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void grpcGetCoOwner(TariffServiceOuterClass.Id request, StreamObserver<TariffServiceOuterClass.CoOwner> responseObserver) {
        int id = request.getId();
        CoOwner coOwner;
        Optional<CoOwner> tempCoOwner = coOwnersRepository.findById(id);
        TariffServiceOuterClass.CoOwner.Builder response =
                TariffServiceOuterClass.CoOwner.newBuilder();
        if(tempCoOwner.isPresent())
        {
            coOwner = tempCoOwner.get();
            response.setId(coOwner.getCoOwnerId());
            response.setName(coOwner.getName());
            response.setAccountBalance(coOwner.getAccountBalance());
        }
        else
        {
            responseObserver.onError(new Exception());
        }
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void grpcGetAllCoOwners(TariffServiceOuterClass.Empty request, StreamObserver<TariffServiceOuterClass.CoOwners> responseObserver) {
        List<CoOwner> coOwners = coOwnersRepository.findAll();
        TariffServiceOuterClass.CoOwners.Builder ownersResponse=
                TariffServiceOuterClass.CoOwners.newBuilder();
        for(CoOwner coOwner : coOwners)
        {
            TariffServiceOuterClass.CoOwner.Builder ownerToList =
                    TariffServiceOuterClass.CoOwner.newBuilder();
            ownerToList.setId(coOwner.getCoOwnerId());
            ownerToList.setName(coOwner.getName());
            ownerToList.setAccountBalance(coOwner.getAccountBalance());
            ownersResponse.addOwners(ownerToList);
        }
        responseObserver.onNext(ownersResponse.build());
        responseObserver.onCompleted();
    }

    @Override
    public void grpcAddToBalance(TariffServiceOuterClass.ChangeBalance request, StreamObserver<TariffServiceOuterClass.Empty> responseObserver) {
       int id = request.getId();
       double income = request.getIncome();
       CoOwner tempCO = coOwnersRepository.findById(id).get();
       tempCO.addIncome(income);
       coOwnersRepository.save(tempCO);
        TariffServiceOuterClass.Empty.Builder response =
                TariffServiceOuterClass.Empty.newBuilder();
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void grpcCollectMoney(TariffServiceOuterClass.Id request, StreamObserver<TariffServiceOuterClass.Empty> responseObserver) {
        super.grpcCollectMoney(request, responseObserver);
    }

    @Override
    public void grpcPayMoney(TariffServiceOuterClass.Empty request, StreamObserver<TariffServiceOuterClass.Empty> responseObserver) {
        super.grpcPayMoney(request, responseObserver);
    }

    @Override
    public void grpcCreateCoOwner(TariffServiceOuterClass.CreateCoOwner request, StreamObserver<TariffServiceOuterClass.Empty> responseObserver) {
        String name = request.getName();
        double accountBalance = request.getAccountBalance();
        CoOwner newCoOwner = new CoOwner(name,accountBalance);
        coOwnersRepository.save(newCoOwner);
        TariffServiceOuterClass.Empty.Builder response = TariffServiceOuterClass.Empty.newBuilder();
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void grpcCreateTariff(TariffServiceOuterClass.CreateTariff request, StreamObserver<TariffServiceOuterClass.Empty> responseObserver) {
        double placePrice = request.getPlacePrice();
        double water = request.getWaterTariff();
        double gas = request.getGasTariff();
        double electricity = request.getElectricityTariff();
        String description = request.getTariffDesc();
        Tariff newTariff = new Tariff(placePrice,water,electricity,gas,description);
        tariffRepository.save(newTariff);
        TariffServiceOuterClass.Empty.Builder response = TariffServiceOuterClass.Empty.newBuilder();
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}
