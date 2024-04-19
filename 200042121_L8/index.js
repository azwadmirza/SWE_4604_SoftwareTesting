function getSales(lock,stock,barrel){
    if(lock<0){
        throw Error("Invalid");
    }
    if(lock>70){
        throw Error("Invalid");
    }
    if(stock>80){
        throw Error("Invalid");
    }
    if(barrel>90){
        throw Error("Invalid");
    }
    if(stock<0){
        throw Error("Invalid");
    }
    if(barrel<0){
        throw Error("Invalid");
    }
    sales=lock*45+stock*30+barrel*25
    if(sales<=1000){
        return (sales*1.1).toFixed(1);
    }
    else if(sales<=1800){
        return (1000*1.1+(sales-1000)*1.15).toFixed(1);
    }
    else{
        return (1000*1.1+800*1.15+(sales-1800)*1.20).toFixed(1);
    }
}

module.exports={getSales}