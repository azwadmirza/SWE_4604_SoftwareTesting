const { getSales } = require("..");

describe('Weak Normal',()=>{
    it('TC01',()=>{
        expect(getSales(10.0,10.0,5.0)).toBe("962.5");
    });
    it('TC02',()=>{
        expect(getSales(10.0,10.0,26.0)).toBe("1560.0");
    });
    it('TC03',()=>{
        expect(getSales(38.0,44.0,50.0)).toBe("4996.0");
    });
})

describe('Strong Robust', () => {
    it('TC01', () => {
        expect(() => { getSales(-1.0, -1.0, -1.0) }).toThrowError();
    });
    it('TC02', () => {
        expect(() => { getSales(-1.0, 34.0, 35.0) }).toThrowError();
    });
    it('TC03', () => {
        expect(() => { getSales(33.0, -1.0, 85.0) }).toThrowError();
    });
    it('TC04', () => {
        expect(() => { getSales(-1.0, 47.0, 95.0) }).toThrowError();
    });
    it('TC05', () => {
        expect(() => { getSales(75.0, -1.0, 53.0) }).toThrowError();
    });
    it('TC06', () => {
        expect(() => { getSales(75.0, 48.0, -1.0) }).toThrowError();
    });
    it('TC07', () => {
        expect(() => { getSales(-1.0, 85.0, 95.0) }).toThrowError();
    });
});



describe('Weak Robust', () => {
    it('TC01', () => {
        expect(() => { getSales(-1.0, 45.0, 46.0) }).toThrowError();
    });
    it('TC02', () => {
        expect(() => { getSales(75.0, 40.0, 30.0) }).toThrowError();
    });
    it('TC03', () => {
        expect(() => { getSales(30.0, -1.0, 50.0) }).toThrowError();
    });
    it('TC04', () => {
        expect(() => { getSales(40.0, 85.0, 40.0) }).toThrowError();
    });
    it('TC05', () => {
        expect(() => { getSales(33.0, 52.0, -1.0) }).toThrowError();
    });
    it('TC06', () => {
        expect(() => { getSales(36.0, 47.0, 95.0) }).toThrowError();
    });
    it('TC07', () => {
        expect(() => { getSales(75.0, 85.0, 95.0) }).toThrowError();
    });
});

